package com.coctailfashionstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.coctailfashionstore.model.Product;
import com.coctailfashionstore.model.ProductVariant;
import com.coctailfashionstore.util.DBConnection;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("brand"), rs.getString("name"), 
                                         rs.getString("description"), rs.getDouble("price"), rs.getString("image_url")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("brand"), rs.getString("name"), 
                                         rs.getString("description"), rs.getDouble("price"), rs.getString("image_url")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("brand"), rs.getString("name"), 
                                      rs.getString("description"), rs.getDouble("price"), rs.getString("image_url"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return product;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();
        // Now it searches the brand name too!
        String query = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ? OR brand LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getInt("category_id"), rs.getString("brand"), rs.getString("name"), 
                                         rs.getString("description"), rs.getDouble("price"), rs.getString("image_url")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return products;
    }
    
    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                product.setCategoryId(rs.getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<ProductVariant> getVariantsByProductId(int productId) {
        List<ProductVariant> variants = new ArrayList<>();
        String query = "SELECT * FROM product_variants WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                variants.add(new ProductVariant(rs.getInt("id"), rs.getInt("product_id"), 
                                                rs.getString("size"), rs.getString("color"), rs.getInt("stock_quantity")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return variants;
    }

    @Override
    public ProductVariant getVariantById(int variantId) {
        ProductVariant variant = null;
        String query = "SELECT * FROM product_variants WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, variantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                variant = new ProductVariant(rs.getInt("id"), rs.getInt("product_id"), 
                                             rs.getString("size"), rs.getString("color"), rs.getInt("stock_quantity"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return variant;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean isSuccess = false;
        String query = "INSERT INTO products (category_id, brand, name, description, price, image_url) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getBrand());
            pstmt.setString(3, product.getName());
            pstmt.setString(4, product.getDescription());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setString(6, product.getImageUrl());
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public boolean addProductVariant(ProductVariant variant) {
        boolean isSuccess = false;
        String query = "INSERT INTO product_variants (product_id, size, color, stock_quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, variant.getProductId());
            pstmt.setString(2, variant.getSize());
            pstmt.setString(3, variant.getColor());
            pstmt.setInt(4, variant.getStockQuantity());
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }

    @Override
    public boolean updateVariantStock(int variantId, int newStockQuantity) {
        boolean isSuccess = false;
        String query = "UPDATE product_variants SET stock_quantity = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, newStockQuantity);
            pstmt.setInt(2, variantId);
            if (pstmt.executeUpdate() > 0) isSuccess = true;
        } catch (SQLException e) { e.printStackTrace(); }
        return isSuccess;
    }
}