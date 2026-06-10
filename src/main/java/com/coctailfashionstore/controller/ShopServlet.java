package com.coctailfashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.coctailfashionstore.dao.CategoryDao;
import com.coctailfashionstore.dao.CategoryDaoImpl;
import com.coctailfashionstore.dao.ProductDao;
import com.coctailfashionstore.dao.ProductDaoImpl;
import com.coctailfashionstore.model.Category;
import com.coctailfashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String categoryParam = request.getParameter("categoryId");
        List<Product> productList;
        String pageTitle = "All Collections"; // Default title

        if (categoryParam != null && !categoryParam.isEmpty()) {
            // A category was selected! Filter the results.
            int categoryId = Integer.parseInt(categoryParam);
            productList = productDao.getProductsByCategory(categoryId);
            
            // Fetch the category name to update the page title
            Category category = categoryDao.getCategoryById(categoryId);
            if (category != null) {
                pageTitle = category.getName();
            }
        } else {
            // No category selected, show the entire catalog
            productList = productDao.getAllProducts();
        }

        // Pass the data to the JSP
        request.setAttribute("products", productList);
        request.setAttribute("pageTitle", pageTitle);
        
        // Forward to the View
        request.getRequestDispatcher("/views/shop.jsp").forward(request, response);
    }
}