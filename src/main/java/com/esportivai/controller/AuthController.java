package com.esportivai.controller;

import com.esportivai.domain.entity.User;
import com.esportivai.application.usecase.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        return authService.login(credentials.get("email"), credentials.get("password"));
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody Map<String, String> body) {
        return authService.forgotPassword(body.get("email"));
    }
}

