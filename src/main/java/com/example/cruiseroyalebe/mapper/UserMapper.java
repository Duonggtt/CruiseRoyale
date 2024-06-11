package com.example.cruiseroyalebe.mapper;

import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.modal.respone.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
}
