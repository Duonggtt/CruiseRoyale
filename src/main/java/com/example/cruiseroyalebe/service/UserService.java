package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Role;
import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.modal.request.RegisterRequest;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
