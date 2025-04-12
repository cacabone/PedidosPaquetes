package controller;

import view.AdminView;
import dao.UserDAO;
import dao.PackageDAO;
import model.User;
import model.Package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {
    private AdminView adminView;
    private UserDAO userDAO;
    private PackageDAO packageDAO;
    
    public AdminController(AdminView adminView, UserDAO userDAO) {
        this.adminView = adminView;
        this.userDAO = userDAO;
        this.packageDAO = new PackageDAO();
        
        // Cargar datos iniciales
        refreshUsers();
        refreshPackages();
        
        // Agregar listeners
        this.adminView.addAddUserListener(new AddUserListener());
        this.adminView.addEditUserListener(new EditUserListener());
        this.adminView.addDeleteUserListener(new DeleteUserListener());
        this.adminView.addAddPackageListener(new AddPackageListener());
        this.adminView.addEditPackageListener(new EditPackageListener());
        this.adminView.addDeletePackageListener(new DeletePackageListener());
        this.adminView.addRefreshListener(new RefreshListener());
    }
    
    private void refreshUsers() {
        List<User> users = userDAO.getAllUsers();
        adminView.updateUsersTable(users);
    }
    
    private void refreshPackages() {
        List<Package> packages = packageDAO.getAllPackages();
        adminView.updatePackagesTable(packages);
    }
    
    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar lógica para agregar usuario
            adminView.showMessage("Funcionalidad para agregar usuario");
        }
    }
    
    class EditUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User selectedUser = adminView.getSelectedUser();
            if (selectedUser != null) {
                // Implementar lógica para editar usuario
                adminView.showMessage("Editando usuario: " + selectedUser.getUsername());
            } else {
                adminView.showMessage("Por favor seleccione un usuario");
            }
        }
    }
    
    class DeleteUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User selectedUser = adminView.getSelectedUser();
            if (selectedUser != null) {
                // Implementar lógica para eliminar usuario
                if (userDAO.deleteUser(selectedUser.getId())) {
                    adminView.showMessage("Usuario eliminado correctamente");
                    refreshUsers();
                } else {
                    adminView.showMessage("Error al eliminar el usuario");
                }
            } else {
                adminView.showMessage("Por favor seleccione un usuario");
            }
        }
    }
    
    class AddPackageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implementar lógica para agregar paquete
            adminView.showMessage("Funcionalidad para agregar paquete");
        }
    }
    
    class EditPackageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Package selectedPackage = adminView.getSelectedPackage();
            if (selectedPackage != null) {
                // Implementar lógica para editar paquete
                adminView.showMessage("Editando paquete ID: " + selectedPackage.getId());
            } else {
                adminView.showMessage("Por favor seleccione un paquete");
            }
        }
    }
    
    class DeletePackageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Package selectedPackage = adminView.getSelectedPackage();
            if (selectedPackage != null) {
                // Implementar lógica para eliminar paquete
                if (packageDAO.deletePackage(selectedPackage.getId())) {
                    adminView.showMessage("Paquete eliminado correctamente");
                    refreshPackages();
                } else {
                    adminView.showMessage("Error al eliminar el paquete");
                }
            } else {
                adminView.showMessage("Por favor seleccione un paquete");
            }
        }
    }
    
    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshUsers();
            refreshPackages();
            adminView.showMessage("Datos actualizados");
        }
    }
}