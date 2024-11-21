package com.esportivai.application.usecase;

import com.esportivai.domain.entity.User;
import com.esportivai.domain.repository.UserRepository;
import com.esportivai.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtTokenProvider.generateToken(email);
        }
        throw new RuntimeException("Invalid credentials!");
    }

    public String forgotPassword(String email) {
        // Simular envio de e-mail
        return "Password recovery instructions sent to " + email;
    }
}
