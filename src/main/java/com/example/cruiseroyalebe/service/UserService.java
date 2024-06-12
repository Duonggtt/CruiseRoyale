package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Role;
import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.modal.request.CreateUserRequest;
import com.example.cruiseroyalebe.modal.request.RegisterRequest;
import com.example.cruiseroyalebe.modal.request.UpdateUserRequest;
import com.example.cruiseroyalebe.modal.respone.UserResponse;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User getUserById(Integer id);
    User getUserByUsername(String username);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    UserResponse getUserResponseById(Integer id);
    List<UserResponse> getAllUsers();
    List<User> getUsers();
    User updateUser(String name, User request);
    UserResponse updateUserById(Integer id, UpdateUserRequest request);
    UserResponse createUser(CreateUserRequest request);
    void removeUserById(Integer id);
    UserResponse getUserResponseByPhone(String phone);
}
