package dao;

import model.Package;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class PackageDAO {
    // Método para insertar un nuevo paquete
    public boolean insertPackage(Package pkg) {
        String sql = "INSERT INTO packages (description, weight, price, status, creation_date, user_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pkg.getDescription());
            pstmt.setDouble(2, pkg.getWeight());
            pstmt.setDouble(3, pkg.getPrice());
            pstmt.setString(4, pkg.getStatus());
            pstmt.setDate(5, new java.sql.Date(pkg.getCreationDate().getTime()));
            pstmt.setInt(6, pkg.getUserId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        String sql = "SELECT * FROM packages";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                packages.add(new Package(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getDouble("weight"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getDate("creation_date"),
                    rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
    
    // Método para obtener todos los paquetes de un usuario
    public List<Package> getPackagesByUser(int userId) {
        List<Package> packages = new ArrayList<>();
        String sql = "SELECT * FROM packages WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                packages.add(new Package(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getDouble("weight"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getDate("creation_date"),
                    rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
    
    public boolean updatePackage(Package pkg) {
        String sql = "UPDATE packages SET description = ?, weight = ?, price = ?, status = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, pkg.getDescription());
            pstmt.setDouble(2, pkg.getWeight());
            pstmt.setDouble(3, pkg.getPrice());
            pstmt.setString(4, pkg.getStatus());
            pstmt.setInt(5, pkg.getId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deletePackage(int packageId) {
        String sql = "DELETE FROM packages WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, packageId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Otros métodos CRUD: getAllPackages, updatePackage, deletePackage
    // ...
}