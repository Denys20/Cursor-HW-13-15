package com.example.SpringSecurity.web;

import com.example.SpringSecurity.dto.NewUserDto;
import com.example.SpringSecurity.dto.UserInfoDto;
import com.example.SpringSecurity.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityScheme(name = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class UserController {
    private final UserService userService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody @Validated NewUserDto newUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUserDto));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "API для видалення користувача за ID користувача")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
