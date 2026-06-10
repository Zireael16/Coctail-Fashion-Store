package com.coctailfashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.coctailfashionstore.dao.OrderDao;
import com.coctailfashionstore.dao.OrderDaoImpl;
import com.coctailfashionstore.model.Order;
import com.coctailfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDao orderDao = new OrderDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // GUARD: Kick them to the login page if they aren't signed in
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Fetch their order history using your custom DAO method
        List<Order> myOrders = orderDao.getOrdersByUserId(currentUser.getId());
        
        // Send the orders to the JSP view
        request.setAttribute("myOrders", myOrders);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}