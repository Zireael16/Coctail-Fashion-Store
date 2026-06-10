package com.coctailfashionstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coctailfashionstore.dao.OrderDao;
import com.coctailfashionstore.dao.OrderDaoImpl;
import com.coctailfashionstore.dao.ProductDao;
import com.coctailfashionstore.dao.ProductDaoImpl;
import com.coctailfashionstore.model.CartItem;
import com.coctailfashionstore.model.Order;
import com.coctailfashionstore.model.OrderItem;
import com.coctailfashionstore.model.Product;
import com.coctailfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // GUARD: Must be logged in
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?msg=checkout");
            return;
        }

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        
        // GUARD: Cart cannot be empty
        if (cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/shop");
            return;
        }

        // FIX FOR YOUR SCREENSHOT: Calculate the cart total before sending to JSP
        double cartTotal = 0;
        for (CartItem item : cartItems) {
            Product product = productDao.getProductById(item.getProductId());
            if (product != null) {
                cartTotal += (product.getPrice() * item.getQuantity());
            }
        }

        request.setAttribute("cartTotal", cartTotal);
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        if (currentUser == null || cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/shop");
            return;
        }

        // 1. Calculate final total securely on the server
        double totalAmount = 0;
        for (CartItem cItem : cartItems) {
            Product product = productDao.getProductById(cItem.getProductId());
            if (product != null) {
                totalAmount += (product.getPrice() * cItem.getQuantity());
            }
        }

        // 2. Create the Order using your model
        Order order = new Order(currentUser.getId(), totalAmount, "Pending", new Date());
        
     // NEW: Grab the address from the HTML form and add it to the order
        String formAddress = request.getParameter("shippingAddress");
        order.setShippingAddress(formAddress);

        // 3. Save Order using YOUR DAO method to get the new ID
        int newOrderId = orderDao.createOrder(order);

        if (newOrderId > 0) {
            // 4. Build the OrderItems list now that we have the orderId
            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem cItem : cartItems) {
                Product product = productDao.getProductById(cItem.getProductId());
                if (product != null) {
                    OrderItem oItem = new OrderItem(newOrderId, cItem.getProductId(), cItem.getVariantId(), cItem.getQuantity(), product.getPrice());
                    orderItems.add(oItem);
                }
            }

            // 5. Save all items using YOUR batch method
            boolean itemsSaved = orderDao.addOrderItems(orderItems);

            if (itemsSaved) {
                // Clear the cart from the session since they bought it
                session.removeAttribute("cart");
                session.removeAttribute("cartItems");
                
                // Redirect to the home page with a success message
                response.sendRedirect(request.getContextPath() + "/?msg=order_success");
            } else {
                response.sendRedirect(request.getContextPath() + "/checkout?error=items_failed");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/checkout?error=order_failed");
        }
    }
}