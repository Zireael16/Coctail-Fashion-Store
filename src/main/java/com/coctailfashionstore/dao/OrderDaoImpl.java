package com.coctailfashionstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.coctailfashionstore.model.Order;
import com.coctailfashionstore.model.OrderItem;
import com.coctailfashionstore.util.DBConnection;

public class OrderDaoImpl implements OrderDao {

    @Override
    public int createOrder(Order order) {
        int orderId = 0;
        String query = "INSERT INTO orders (user_id, total_amount, status, order_date, shipping_address) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             // Request the generated key back from MySQL
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, order.getUserId());
            pstmt.setDouble(2, order.getTotalAmount());
            pstmt.setString(3, order.getStatus());
            pstmt.setDate(4, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setString(5, order.getShippingAddress());
            
            pstmt.executeUpdate();
            
            // Retrieve the newly created order ID
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return orderId;
    }

    @Override
    public boolean addOrderItems(List<OrderItem> items) {
        boolean isSuccess = false;
        // FIXED: Only 4 columns, exactly matching your MySQL table screenshot
        String query = "INSERT INTO order_items (order_id, product_variant_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            for (OrderItem item : items) {
                pstmt.setInt(1, item.getOrderId());
                pstmt.setInt(2, item.getVariantId()); // Maps to product_variant_id
                pstmt.setInt(3, item.getQuantity());
                pstmt.setDouble(4, item.getPriceAtPurchase());
                pstmt.addBatch();
            }
            
            int[] rows = pstmt.executeBatch();
            if (rows.length > 0) isSuccess = true;
            
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("total_amount"), 
                                  rs.getString("status"), rs.getDate("order_date"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("total_amount"), 
                                     rs.getString("status"), rs.getDate("order_date")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return orders;
    }

    
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String query = "SELECT * FROM order_items WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // FIXED: We pass '0' for the productId since it's not in the DB, and read 'product_variant_id'
                items.add(new OrderItem(
                    rs.getInt("id"), 
                    rs.getInt("order_id"), 
                    0, // Dummy product_id 
                    rs.getInt("product_variant_id"), 
                    rs.getInt("quantity"), 
                    rs.getDouble("price_at_purchase")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return items;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        boolean isSuccess = false;
        String query = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }
}