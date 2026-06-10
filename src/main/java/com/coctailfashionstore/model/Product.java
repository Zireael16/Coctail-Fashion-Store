package com.coctailfashionstore.model;

public class Product {
    private int id;
    private int categoryId;
    private String brand; // NEW FIELD
    private String name;
    private String description;
    private double price;
    private String imageUrl;

    // Empty Constructor
    public Product() {}

    // Constructor without ID (for inserting new products)
    public Product(int categoryId, String brand, String name, String description, double price, String imageUrl) {
        this.categoryId = categoryId;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Constructor with ID (for fetching from database)
    public Product(int id, int categoryId, String brand, String name, String description, double price, String imageUrl) {
        this.id = id;
        this.categoryId = categoryId;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}