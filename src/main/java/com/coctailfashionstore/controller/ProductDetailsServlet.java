package com.coctailfashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.coctailfashionstore.dao.ProductDao;
import com.coctailfashionstore.dao.ProductDaoImpl;
import com.coctailfashionstore.model.Product;
import com.coctailfashionstore.model.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("shop");
            return;
        }

        try {
            int productId = Integer.parseInt(idParam);
            Product product = productDao.getProductById(productId);
            List<ProductVariant> variants = productDao.getVariantsByProductId(productId);

            if (product != null) {
                request.setAttribute("product", product);
                request.setAttribute("variants", variants);
                request.getRequestDispatcher("/views/product-details.jsp").forward(request, response);
            } else {
                response.sendRedirect("shop");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("shop");
        }
    }
}