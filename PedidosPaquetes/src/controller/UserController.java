package controller;

import view.UserView;
import dao.PackageDAO;
import model.Package;
import model.User;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class UserController {
    private UserView userView;
    private PackageDAO packageDAO;
    private User currentUser;
    
    public UserController(UserView userView, User currentUser) {
        this.userView = userView;
        this.currentUser = currentUser;
        this.packageDAO = new PackageDAO();
        
        // Cargar paquetes del usuario
        refreshPackages();
        
        // Agregar listeners
        this.userView.addAddPackageListener(new AddPackageListener());
        this.userView.addEditPackageListener(new EditPackageListener());
        this.userView.addDeletePackageListener(new DeletePackageListener());
        this.userView.addRefreshListener(new RefreshListener());
        this.userView.addLogoutListener(new LogoutListener());
    }
        
    private void refreshPackages() {
        List<Package> packages = packageDAO.getPackagesByUser(currentUser.getId());
        userView.updatePackagesTable(packages);
    }
    
    class AddPackageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mostrar diálogo para agregar paquete
            String description = JOptionPane.showInputDialog(userView, "Descripción del paquete:");
            if (description == null || description.isEmpty()) return;
            
            String weightStr = JOptionPane.showInputDialog(userView, "Peso del paquete (kg):");
            if (weightStr == null || weightStr.isEmpty()) return;
            
            String priceStr = JOptionPane.showInputDialog(userView, "Precio del paquete:");
            if (priceStr == null || priceStr.isEmpty()) return;
            
            try {
                double weight = Double.parseDouble(weightStr);
                double price = Double.parseDouble(priceStr);
                
                Package newPackage = new Package(
                    0, // ID se generará automáticamente
                    description,
                    weight,
                    price,
                    "pendiente", // Estado inicial
                    new Date(),
                    currentUser.getId()
                );
                
                if (packageDAO.insertPackage(newPackage)) {
                    userView.showMessage("Paquete agregado correctamente");
                    refreshPackages();
                } else {
                    userView.showMessage("Error al agregar el paquete");
                }
            } catch (NumberFormatException ex) {
                userView.showMessage("Por favor ingrese valores numéricos válidos para peso y precio");
            }
        }
    }
    
    class EditPackageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Package selectedPackage = userView.getSelectedPackage();
            if (selectedPackage != null) {
                // Mostrar diálogo para editar paquete
                String description = JOptionPane.showInputDialog(
                    userView, 
                    "Nueva descripción:", 
                    selectedPackage.getDescription()
                );
                
                if (description == null) return; // El usuario canceló
                
                String weightStr = JOptionPane.showInputDialog(
                    userView, 
                    "Nuevo peso (kg):", 
                    selectedPackage.getWeight()
                );
                
                if (weightStr == null) return;
                
                String priceStr = JOptionPane.showInputDialog(
                    userView, 
                    "Nuevo precio:", 
                    selectedPackage.getPrice()
                );
                
                if (priceStr == null) return;
                
                try {
                    double weight = Double.parseDouble(weightStr);
                    double price = Double.parseDouble(priceStr);
                    
                    // Actualizar el paquete seleccionado con los nuevos valores
                    selectedPackage.setDescription(description);
                    selectedPackage.setWeight(weight);
                    selectedPackage.setPrice(price);
                    
                    if (packageDAO.updatePackage(selectedPackage)) {
                        userView.showMessage("Paquete actualizado correctamente");
                        refreshPackages();
                    } else {
                        userView.showMessage("Error al actualizar el paquete");
                    }
                } catch (NumberFormatException ex) {
                    userView.showMessage("Por favor ingrese valores numéricos válidos para peso y precio");
                }
            } else {
                userView.showMessage("Por favor seleccione un paquete");
            }
        }
    }
    
    class DeletePackageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Package selectedPackage = userView.getSelectedPackage();
            if (selectedPackage != null) {
                int confirm = JOptionPane.showConfirmDialog(
                    userView, 
                    "¿Está seguro de eliminar este paquete?", 
                    "Confirmar eliminación", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (packageDAO.deletePackage(selectedPackage.getId())) {
                        userView.showMessage("Paquete eliminado correctamente");
                        refreshPackages();
                    } else {
                        userView.showMessage("Error al eliminar el paquete");
                    }
                }
            } else {
                userView.showMessage("Por favor seleccione un paquete");
            }
        }
    }
    
    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshPackages();
            userView.showMessage("Datos actualizados");
        }
    }
    
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userView.dispose();
            
        }
    }
}