package com.coctailfashionstore.dao;

import java.util.List;
import com.coctailfashionstore.model.User;

public interface UserDao {
    // Core Authentication
    boolean registerUser(User user);
    User loginUser(String email, String password);
    
    // Future-proofing (Profile management, Admin panels)
    User getUserById(int id);
    boolean updateUser(User user);
    List<User> getAllUsers();
}