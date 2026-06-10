package com.coctailfashionstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coctailfashionstore.dao.ProductDao;
import com.coctailfashionstore.dao.ProductDaoImpl;
import com.coctailfashionstore.model.Cart;
import com.coctailfashionstore.model.CartItem;
import com.coctailfashionstore.model.Product;
import com.coctailfashionstore.model.ProductVariant;
import com.coctailfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/cart", "/add-to-cart", "/remove-from-cart"})
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDaoImpl();

    // --- INNER CLASS: Used only to bundle data for the JSP View ---
    public class CartDetail {
        public CartItem cartItem;
        public Product product;
        public ProductVariant variant;
        public double subtotal;

        public CartDetail(CartItem cartItem, Product product, ProductVariant variant) {
            this.cartItem = cartItem;
            this.product = product;
            this.variant = variant;
            this.subtotal = product.getPrice() * cartItem.getQuantity();
        }
        
        public CartItem getCartItem() { return cartItem; }
        public Product getProduct() { return product; }
        public ProductVariant getVariant() { return variant; }
        public double getSubtotal() { return subtotal; }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getServletPath();
        HttpSession session = request.getSession();
        
        // Retrieve the list of CartItems
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) cartItems = new ArrayList<>();

        // Handle item removal
        if ("/remove-from-cart".equals(action)) {
            int variantIdToRemove = Integer.parseInt(request.getParameter("variantId"));
            cartItems.removeIf(item -> item.getVariantId() == variantIdToRemove);
            session.setAttribute("cartItems", cartItems);
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Bundle the IDs with actual Product data for the View
        List<CartDetail> cartDetails = new ArrayList<>();
        double cartTotal = 0;

        for (CartItem item : cartItems) {
            Product product = productDao.getProductById(item.getProductId());
            ProductVariant variant = productDao.getVariantById(item.getVariantId());
            
            if (product != null && variant != null) {
                CartDetail detail = new CartDetail(item, product, variant);
                cartDetails.add(detail);
                cartTotal += detail.getSubtotal();
            }
        }
        
        request.setAttribute("cartDetails", cartDetails);
        request.setAttribute("cartTotal", cartTotal);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        
        HttpSession session = request.getSession();
        
        // 1. Manage the Cart Object
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            User currentUser = (User) session.getAttribute("currentUser");
            // If logged in, tie cart to user. If guest, set userId to 0.
            int userId = (currentUser != null) ? currentUser.getId() : 0;
            cart = new Cart(userId);
            session.setAttribute("cart", cart);
        }

        // 2. Manage the Cart Items List
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Check if item exists to update quantity
        boolean itemExists = false;
        for (CartItem item : cartItems) {
            if (item.getVariantId() == variantId) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        // If new, create your CartItem model (using cart.getId() to link them)
        if (!itemExists) {
            CartItem newItem = new CartItem(0, cart.getId(), productId, variantId, 1);
            cartItems.add(newItem);
        }

        session.setAttribute("cartItems", cartItems);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}