package com.esportivai.controllers;

import com.esportivai.domain.dtos.JwtResponse;
import com.esportivai.domain.dtos.LoginRequest;
import com.esportivai.domain.dtos.UserDto;
import com.esportivai.domain.entity.User;
import com.esportivai.application.usecase.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5500")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto user) {
        User newUser = authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        JwtResponse token = authService.login(loginRequest);

        if (token == null) {
            return ResponseEntity.status(401).body(new JwtResponse("Invalid credentials"));
        }

        return ResponseEntity.ok(token);
    }

}

