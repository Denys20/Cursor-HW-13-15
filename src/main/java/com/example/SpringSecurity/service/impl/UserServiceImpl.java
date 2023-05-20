package com.example.SpringSecurity.service.impl;

import com.example.SpringSecurity.dto.NewUserDto;
import com.example.SpringSecurity.dto.UserDto;
import com.example.SpringSecurity.dto.UserInfoDto;
import com.example.SpringSecurity.entity.User;
import com.example.SpringSecurity.exception.NotFoundException;
import com.example.SpringSecurity.repository.UserRepositoty;
import com.example.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoty userRepositoty;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(NewUserDto newUserDto) {
        User user = modelMapper.map(newUserDto, User.class);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return modelMapper.map(userRepositoty.save(user), UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepositoty.findByEmail(email).orElseThrow(() -> new NotFoundException("Користувача з цією електронною поштою не знайдено!"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserInfoDto> getAllUsers() {
        List<User> users = (List<User>)userRepositoty.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepositoty.deleteById(userId);
    }
}
