package com.example.SpringSecurity.repository;

import com.example.SpringSecurity.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoty extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
