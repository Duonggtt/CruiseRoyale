package com.example.cruiseroyalebe.modal.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private List<Integer> roleIds;
}
