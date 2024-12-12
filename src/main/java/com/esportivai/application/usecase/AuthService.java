package com.esportivai.application.usecase;

import com.esportivai.domain.dtos.JwtResponse;
import com.esportivai.domain.dtos.LoginRequest;
import com.esportivai.domain.dtos.UserDto;
import com.esportivai.domain.entity.User;
import com.esportivai.domain.repository.UserRepository;
import com.esportivai.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User register(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public JwtResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return new JwtResponse(jwtTokenProvider.generateToken(loginRequest.getEmail()));
        }
            return null;
    }

}
