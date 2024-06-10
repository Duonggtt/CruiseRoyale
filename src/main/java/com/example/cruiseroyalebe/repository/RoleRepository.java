package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
    @Query("SELECT u.roles FROM User u WHERE u.id = ?1")
    List<Role> findAllByUserId(Integer userId);

    @Query("SELECT r.id FROM Role r JOIN User u WHERE u.id = ?1")
    List<Integer> getAllRoleIdsByUserId(Integer userId);
}
