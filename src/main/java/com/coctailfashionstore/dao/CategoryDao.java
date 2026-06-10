package com.coctailfashionstore.dao;

import java.util.List;
import com.coctailfashionstore.model.Category;

public interface CategoryDao {
    // Core Catalog
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    
    // Future-proofing (Admin panel)
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
}