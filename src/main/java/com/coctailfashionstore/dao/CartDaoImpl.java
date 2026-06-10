package com.coctailfashionstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.coctailfashionstore.model.Cart;
import com.coctailfashionstore.model.CartItem;
import com.coctailfashionstore.util.DBConnection;

public class CartDaoImpl implements CartDao {

    @Override
    public Cart getCartByUserId(int userId) {
        Cart cart = null;
        String query = "SELECT * FROM cart WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cart = new Cart(rs.getInt("id"), rs.getInt("user_id"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return cart;
    }

    @Override
    public boolean createCart(Cart cart) {
        boolean isSuccess = false;
        String query = "INSERT INTO cart (user_id) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cart.getUserId());
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public boolean addCartItem(CartItem item) {
        boolean isSuccess = false;
        String query = "INSERT INTO cart_items (cart_id, product_id, variant_id, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, item.getCartId());
            pstmt.setInt(2, item.getProductId());
            pstmt.setInt(3, item.getVariantId());
            pstmt.setInt(4, item.getQuantity());
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public List<CartItem> getCartItems(int cartId) {
        List<CartItem> items = new ArrayList<>();
        String query = "SELECT * FROM cart_items WHERE cart_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cartId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(new CartItem(rs.getInt("id"), rs.getInt("cart_id"), rs.getInt("product_id"), 
                                       rs.getInt("variant_id"), rs.getInt("quantity")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return items;
    }

    @Override
    public boolean updateCartItemQuantity(int cartItemId, int newQuantity) {
        boolean isSuccess = false;
        String query = "UPDATE cart_items SET quantity = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, cartItemId);
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public boolean removeCartItem(int cartItemId) {
        boolean isSuccess = false;
        String query = "DELETE FROM cart_items WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cartItemId);
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public boolean clearCart(int cartId) {
        boolean isSuccess = false;
        String query = "DELETE FROM cart_items WHERE cart_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cartId);
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }
}