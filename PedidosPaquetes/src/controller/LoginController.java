package controller;

import view.LoginView;
import view.RegisterView;
import view.AdminView;
import view.UserView;
import dao.UserDAO;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private RegisterView registerView;
    private UserDAO userDAO;
    
    public LoginController(LoginView loginView, RegisterView registerView, UserDAO userDAO) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.userDAO = userDAO;
        
        // Agregar listeners a los botones
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addRegisterListener(new RegisterListener());
    }
    
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            
            if (username.isEmpty() || password.isEmpty()) {
                loginView.showMessage("Por favor complete todos los campos");
                return;
            }
            
            User user = userDAO.getUserByCredentials(username, password);
            
            if (user != null) {
                loginView.dispose();
                
                if ("admin".equals(user.getRole())) {
                    AdminView adminView = new AdminView();
                    AdminController adminController = new AdminController(adminView, userDAO);
                    adminView.setVisible(true);
                } else {
                    UserView userView = new UserView();
                    UserController userController = new UserController(userView, user);
                    userView.setVisible(true);
                }
            } else {
                loginView.showMessage("Usuario o contraseña incorrectos");
            }
        }
    }
    
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.setVisible(false);
            registerView.setVisible(true);
        }
    }
}