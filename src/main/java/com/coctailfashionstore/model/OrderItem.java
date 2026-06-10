package com.coctailfashionstore.model;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int variantId;
    private int quantity;
    private double priceAtPurchase; // Important to lock in the price at the time of sale!

    public OrderItem() {}

    public OrderItem(int orderId, int productId, int variantId, int quantity, double priceAtPurchase) {
        this.orderId = orderId;
        this.productId = productId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public OrderItem(int id, int orderId, int productId, int variantId, int quantity, double priceAtPurchase) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(double priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
}