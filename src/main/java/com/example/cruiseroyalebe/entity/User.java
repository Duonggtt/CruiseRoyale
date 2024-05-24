package com.example.cruiseroyalebe.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
