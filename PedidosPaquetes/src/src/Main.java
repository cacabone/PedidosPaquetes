package src;

import view.LoginView;
import view.RegisterView;
import dao.UserDAO;
import controller.LoginController;
import controller.RegisterController;

public class Main {
    public static void main(String[] args) {
        // Crear las vistas
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        
        // Crear el DAO
        UserDAO userDAO = new UserDAO();
        
        // Crear los controladores
        LoginController loginController = new LoginController(loginView, registerView, userDAO);
        RegisterController registerController = new RegisterController(registerView, loginView, userDAO);
        
        // Mostrar la vista de login
        loginView.setVisible(true);
    }
}