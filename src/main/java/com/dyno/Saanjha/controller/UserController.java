package com.dyno.Saanjha.controller;

import com.dyno.Saanjha.dto.UserDTO;
import com.dyno.Saanjha.model.User;
import com.dyno.Saanjha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public void register(@RequestBody User newUser){
        userService.save(newUser);
    }

    @PutMapping("users/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        userService.update(userId, updatedUser);
    }

    @GetMapping("users")
    public List<UserDTO> viewUser(){
        return userService.findAll()
                .stream()
                .map(UserDTO:: new )
                .collect(Collectors.toList());
    }

    @PutMapping("users/{userId}/change-password")
    public void updatePassword(@PathVariable String userId, @RequestBody String newPassword) {
        userService.updatePassword(userId, newPassword);
    }

}
