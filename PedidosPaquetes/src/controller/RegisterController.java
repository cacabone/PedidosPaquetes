package controller;

import view.RegisterView;
import view.LoginView;
import dao.UserDAO;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    private RegisterView registerView;
    private LoginView loginView;
    private UserDAO userDAO;
    
    public RegisterController(RegisterView registerView, LoginView loginView, UserDAO userDAO) {
        this.registerView = registerView;
        this.loginView = loginView;
        this.userDAO = userDAO;
        
        // Agregar listeners a los botones
        this.registerView.addRegisterListener(new RegisterListener());
        this.registerView.addBackListener(new BackListener());
    }
    
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = registerView.getUsername();
            String email = registerView.getEmail();
            String password = registerView.getPassword();
            String confirmPassword = registerView.getConfirmPassword();
            
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                registerView.showMessage("Por favor complete todos los campos");
                return;
            }
            
            if (!password.equals(confirmPassword)) {
                registerView.showMessage("Las contraseñas no coinciden");
                return;
            }
            
            // Por defecto, los nuevos usuarios son de tipo "user"
            User newUser = new User(0, username, password, email, "user");
            
            if (userDAO.insertUser(newUser)) {
                registerView.showMessage("Registro exitoso. Por favor inicie sesión.");
                registerView.dispose();
                loginView.setVisible(true);
            } else {
                registerView.showMessage("Error al registrar el usuario. Intente nuevamente.");
            }
        }
    }
    
    class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerView.dispose();
            loginView.setVisible(true);
        }
    }
}