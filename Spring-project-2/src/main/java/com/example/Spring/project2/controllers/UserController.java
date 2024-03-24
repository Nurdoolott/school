package com.example.Spring.project2.controllers;

import com.example.Spring.project2.dto.user.UserRequest;
import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final MyUserDetailsService service;

    @GetMapping("/getAll")
    public List<UserResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByEmail/{email}")
    public UserResponse findByName(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PutMapping("/updateByEmail/{email}")
    public String updateByName(@PathVariable String email, @RequestBody UserRequest userRequest) {
        service.updateByEmail(email, userRequest);
        return "User successfully updated!";
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public String eteByName(@PathVariable String email) {
        service.deleteByEmail(email);
        return "User successfully deleted!";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest) {
        service.register(userRequest);
        return "User successfully registered!";
    }
}
