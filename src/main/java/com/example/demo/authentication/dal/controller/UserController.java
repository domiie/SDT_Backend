package com.example.demo.authentication.dal.controller;

import com.example.demo.authentication.dal.service.PasswordDto;
import com.example.demo.authentication.dal.service.UserService;
import com.example.demo.authentication.dal.service.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public Long createUser(@RequestBody UserDto user){
        return userService.createUser(user);
    }

    @GetMapping("/api/users")
    public List<UserDto> getUsers(@RequestParam(required = false) String role){
        return userService.getUsers(role);
    }

    @GetMapping("/api/users/{userId}")
    public UserDto getUserByUsername(@PathVariable Long userId){
        return userService.getUserByUserId(userId);
    }

//    @GetMapping("/api/users/role/{username}")
//    public UserRolesDto getUserByUsername(@PathVariable String username){
//        return userService.getUserByUsername(username);
//    }

    @PutMapping("/api/users/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserDto user){
        userService.updateUser(userId, user);
    }

    @PutMapping("/api/users/password/{userId}")
    public void updatePassword(@PathVariable Long userId, @RequestBody PasswordDto data){
        userService.updatePassword(userId, data);
    }

    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

}
