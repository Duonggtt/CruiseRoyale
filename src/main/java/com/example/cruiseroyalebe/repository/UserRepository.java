package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.modal.respone.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @Query("Select u from User u")
    List<UserResponse> findAllUsers();
    void deleteById(Integer id);
    @Query("Select u from User u where u.phone = ?1")
    User findByPhone(String phone);
}
