package com.coctailfashionstore.dao;

import java.util.List;
import com.coctailfashionstore.model.Order;
import com.coctailfashionstore.model.OrderItem;

public interface OrderDao {
    // Core Checkout Process
    // Returns the newly generated Order ID so we can attach the OrderItems to it
    int createOrder(Order order); 
    boolean addOrderItems(List<OrderItem> items);
    
    // Fetching Data for User Profiles / Admin Panels
    Order getOrderById(int orderId);
    List<Order> getOrdersByUserId(int userId);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    
    // Order Management
    boolean updateOrderStatus(int orderId, String status);
}