package com.coctailfashionstore.controller;

import java.io.IOException;

import com.coctailfashionstore.dao.UserDao;
import com.coctailfashionstore.dao.UserDaoImpl;
import com.coctailfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            // Collecting all 5 fields matching your User model
            User user = new User(
                request.getParameter("name"), 
                request.getParameter("email"), 
                request.getParameter("password"), 
                request.getParameter("phone"),
                request.getParameter("address")
            );
            
            if (userDao.registerUser(user)) {
                // Registration success
                response.sendRedirect(request.getContextPath() + "/login.jsp?msg=success");
            } else {
                // Registration failed (e.g., email already exists)
                response.sendRedirect(request.getContextPath() + "/register.jsp?error=failed");
            }
            
        } else if ("login".equals(action)) {
            User user = userDao.loginUser(
                request.getParameter("email"), 
                request.getParameter("password")
            );
            
            if (user != null) {
                // Login success: Store user in session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                // Login failed: Invalid credentials
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid");
            }
        }
    }
}