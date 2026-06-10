package com.coctailfashionstore.dao;

import java.util.List;
import com.coctailfashionstore.model.Cart;
import com.coctailfashionstore.model.CartItem;

public interface CartDao {
    // Managing the Cart Session
    Cart getCartByUserId(int userId);
    boolean createCart(Cart cart);
    
    // Managing the Items in the Cart
    boolean addCartItem(CartItem item);
    List<CartItem> getCartItems(int cartId);
    boolean updateCartItemQuantity(int cartItemId, int newQuantity);
    boolean removeCartItem(int cartItemId);
    boolean clearCart(int cartId); // Called after successful checkout
}