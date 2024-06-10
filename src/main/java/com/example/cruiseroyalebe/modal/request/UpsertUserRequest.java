package com.example.cruiseroyalebe.modal.request;

import com.example.cruiseroyalebe.entity.Role;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertUserRequest {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;
    private List<Integer> roleIds;
}
