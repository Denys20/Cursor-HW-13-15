package com.example.SpringSecurity.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
}
