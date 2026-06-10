package com.coctailfashionstore.dao;

import java.util.List;
import com.coctailfashionstore.model.Product;
import com.coctailfashionstore.model.ProductVariant;

public interface ProductDao {
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryId(int categoryId);
    Product getProductById(int id);
    
    // THIS IS THE LINE THAT IS MISSING OR UNSAVED
    List<Product> searchProducts(String keyword);
    List<Product> getProductsByCategory(int categoryId);
    List<ProductVariant> getVariantsByProductId(int productId);
    ProductVariant getVariantById(int variantId);
    
    boolean addProduct(Product product);
    boolean addProductVariant(ProductVariant variant);
    boolean updateVariantStock(int variantId, int newStockQuantity);
}