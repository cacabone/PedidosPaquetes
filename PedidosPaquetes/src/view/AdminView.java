package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.User;
import model.Package;

public class AdminView extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel usersPanel;
    private JPanel packagesPanel;
    
    // Componentes para la gestión de usuarios
    private JTable usersTable;
    private JButton addUserButton;
    private JButton editUserButton;
    private JButton deleteUserButton;
    
    // Componentes para la gestión de paquetes
    private JTable packagesTable;
    private JButton addPackageButton;
    private JButton editPackageButton;
    private JButton deletePackageButton;
    private JButton refreshButton;
    
    public AdminView() {
        setTitle("Panel de Administración");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tabbedPane = new JTabbedPane();
        
        // Panel de usuarios
        usersPanel = new JPanel(new BorderLayout());
        initializeUsersPanel();
        tabbedPane.addTab("Usuarios", usersPanel);
        
        // Panel de paquetes
        packagesPanel = new JPanel(new BorderLayout());
        initializePackagesPanel();
        tabbedPane.addTab("Paquetes", packagesPanel);
        
        add(tabbedPane);
    }
    
    private void initializeUsersPanel() {
        // Tabla de usuarios
        String[] columnNames = {"ID", "Usuario", "Email", "Rol"};
        Object[][] data = {}; // Datos vacíos inicialmente
        
        usersTable = new JTable(data, columnNames);
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        usersPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addUserButton = new JButton("Agregar Usuario");
        editUserButton = new JButton("Editar Usuario");
        deleteUserButton = new JButton("Eliminar Usuario");
        refreshButton = new JButton("Actualizar");
        
        buttonPanel.add(addUserButton);
        buttonPanel.add(editUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(refreshButton);
        
        usersPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void initializePackagesPanel() {
        // Tabla de paquetes
        String[] columnNames = {"ID", "Descripción", "Peso", "Precio", "Estado", "Fecha", "Usuario ID"};
        Object[][] data = {}; // Datos vacíos inicialmente
        
        packagesTable = new JTable(data, columnNames);
        packagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(packagesTable);
        packagesPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addPackageButton = new JButton("Agregar Paquete");
        editPackageButton = new JButton("Editar Paquete");
        deletePackageButton = new JButton("Eliminar Paquete");
        refreshButton = new JButton("Actualizar");
        
        buttonPanel.add(addPackageButton);
        buttonPanel.add(editPackageButton);
        buttonPanel.add(deletePackageButton);
        buttonPanel.add(refreshButton);
        
        packagesPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // Métodos para actualizar las tablas
    public void updateUsersTable(List<User> users) {
        String[] columnNames = {"ID", "Usuario", "Email", "Rol"};
        Object[][] data = new Object[users.size()][4];
        
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getUsername();
            data[i][2] = user.getEmail();
            data[i][3] = user.getRole();
        }
        
        usersTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
    
    public void updatePackagesTable(List<Package> packages) {
        String[] columnNames = {"ID", "Descripción", "Peso", "Precio", "Estado", "Fecha", "Usuario ID"};
        Object[][] data = new Object[packages.size()][7];
        
        for (int i = 0; i < packages.size(); i++) {
            Package pkg = packages.get(i);
            data[i][0] = pkg.getId();
            data[i][1] = pkg.getDescription();
            data[i][2] = pkg.getWeight();
            data[i][3] = pkg.getPrice();
            data[i][4] = pkg.getStatus();
            data[i][5] = pkg.getCreationDate();
            data[i][6] = pkg.getUserId();
        }
        
        packagesTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
    
    // Métodos para obtener selecciones
    public User getSelectedUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow >= 0) {
            return new User(
                (int) usersTable.getValueAt(selectedRow, 0),
                (String) usersTable.getValueAt(selectedRow, 1),
                "", // No mostramos la contraseña
                (String) usersTable.getValueAt(selectedRow, 2),
                (String) usersTable.getValueAt(selectedRow, 3)
            );
        }
        return null;
    }
    
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
                (int) packagesTable.getValueAt(selectedRow, 6)
            );
        }
        return null;
    }
    
    // Listeners
    public void addAddUserListener(ActionListener listener) {
        addUserButton.addActionListener(listener);
    }
    
    public void addEditUserListener(ActionListener listener) {
        editUserButton.addActionListener(listener);
    }
    
    public void addDeleteUserListener(ActionListener listener) {
        deleteUserButton.addActionListener(listener);
    }
    
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
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}