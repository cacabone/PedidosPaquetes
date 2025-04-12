package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.Package;

public class UserView extends JFrame {
    private JTable packagesTable;
    private JButton addPackageButton;
    private JButton editPackageButton;
    private JButton deletePackageButton;
    private JButton refreshButton;
    private JButton logoutButton;

    public UserView() {
        setTitle("Panel de Usuario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Tabla de paquetes
        String[] columnNames = {"ID", "Descripción", "Peso", "Precio", "Estado", "Fecha"};
        Object[][] data = {}; // Datos vacíos inicialmente
        
        packagesTable = new JTable(data, columnNames);
        packagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(packagesTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addPackageButton = new JButton("Agregar Paquete");
        editPackageButton = new JButton("Editar Paquete");
        deletePackageButton = new JButton("Eliminar Paquete");
        refreshButton = new JButton("Actualizar");
        logoutButton = new JButton("Cerrar Sesión");
        
        buttonPanel.add(addPackageButton);
        buttonPanel.add(editPackageButton);
        buttonPanel.add(deletePackageButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    // Método para actualizar la tabla de paquetes
    public void updatePackagesTable(List<Package> packages) {
        String[] columnNames = {"ID", "Descripción", "Peso", "Precio", "Estado", "Fecha"};
        Object[][] data = new Object[packages.size()][6];
        
        for (int i = 0; i < packages.size(); i++) {
            Package pkg = packages.get(i);
            data[i][0] = pkg.getId();
            data[i][1] = pkg.getDescription();
            data[i][2] = pkg.getWeight();
            data[i][3] = pkg.getPrice();
            data[i][4] = pkg.getStatus();
            data[i][5] = pkg.getCreationDate();
        }
        
        packagesTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
    
    // Método para obtener el paquete seleccionado
    public Package getSelectedPackage() {
        int selectedRow = packagesTable.getSelectedRow();
        if (selectedRow >= 0) {
            return new Package(
                (int) packagesTable.getValueAt(selectedRow, 0),
                (String) packagesTable.getValueAt(selectedRow, 1),
                (double) packagesTable.getValueAt(selectedRow, 2),
                (double) packagesTable.getValueAt(selectedRow, 3),
                (String) packagesTable.getValueAt(selectedRow, 4),
                (java.util.Date) packagesTable.getValueAt(selectedRow, 5),
                0 // El user_id no es necesario aquí
            );
        }
        return null;
    }
    
    // Listeners
    public void addAddPackageListener(ActionListener listener) {
        addPackageButton.addActionListener(listener);
    }
    
    public void addEditPackageListener(ActionListener listener) {
        editPackageButton.addActionListener(listener);
    }
    
    public void addDeletePackageListener(ActionListener listener) {
        deletePackageButton.addActionListener(listener);
    }
    
    public void addRefreshListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }
    
    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}