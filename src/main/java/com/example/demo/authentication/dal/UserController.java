package com.example.demo.authentication.dal;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //CREATE USER
    @PostMapping("/api/users")
    public Long createUser(@RequestBody UserDto user){
        return userService.createUser(user);
    }

    //LIST USER
    @GetMapping("/api/users")
    public List<UserDto> getUsers(@RequestParam(required = false) String lastname){
        return userService.getUsers(lastname);
    }

    //GET USER BY ID
    @GetMapping("/api/users/{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    //UPDATE USER
    @PutMapping("/api/users/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserDto user){
        userService.updateUser(userId, user);
    }

    //DELETE USER
    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

}
