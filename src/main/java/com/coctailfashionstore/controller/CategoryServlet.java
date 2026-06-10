package com.coctailfashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.coctailfashionstore.dao.CategoryDao;
import com.coctailfashionstore.dao.CategoryDaoImpl;
import com.coctailfashionstore.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Category> categories = categoryDao.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/categories.jsp").forward(request, response);
    }
}