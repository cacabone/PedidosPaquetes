package model;

import java.util.Date;

public class Package {
    private int id;
    private String description;
    private double weight;
    private double price;
    private String status;
    private Date creationDate;
    private int userId;
    
    // Constructor
    public Package(int id, String description, double weight, double price, 
                  String status, Date creationDate, int userId) {
        this.id = id;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.status = status;
        this.creationDate = creationDate;
        this.userId = userId;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getStatus() {
        return status;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public int getUserId() {
        return userId;
    }
    
    // Setters (opcional, pero recomendado)
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    // ... otros setters según necesidad
}