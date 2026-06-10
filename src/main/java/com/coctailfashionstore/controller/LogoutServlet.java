package com.coctailfashionstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Fetch the session if it exists, but don't create a new one
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // This completely destroys the user session
        }
        
        // Redirect back to the home page after logging out
        response.sendRedirect(request.getContextPath() + "/");
    }
}