package com.coctailfashionstore.model;

public class Cart {
    private int id;
    private int userId;

    public Cart() {}

    public Cart(int userId) {
        this.userId = userId;
    }

    public Cart(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}