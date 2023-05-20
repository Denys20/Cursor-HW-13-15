package com.example.SpringSecurity.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$",
            message = "Недійсний формат електронної пошти")
    private String username;
    private String password;
}
