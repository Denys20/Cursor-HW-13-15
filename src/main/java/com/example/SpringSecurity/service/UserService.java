package com.example.SpringSecurity.service;

import com.example.SpringSecurity.dto.NewUserDto;
import com.example.SpringSecurity.dto.UserDto;
import com.example.SpringSecurity.dto.UserInfoDto;

import java.util.List;

public interface UserService {
    UserDto createUser(NewUserDto newUserDto);

    UserDto getUserByEmail(String email);

    List<UserInfoDto> getAllUsers();

    void deleteUser(Long userId);
}
