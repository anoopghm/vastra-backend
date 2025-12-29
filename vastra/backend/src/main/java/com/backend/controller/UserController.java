package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.DTO.RegisterRequest;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.repository.UserRepository;

@RestController
public class UserController {

    private final PasswordEncoder encoder;
    private final UserRepository userRepo;

    public UserController(PasswordEncoder encoder, UserRepository userRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(authentication.getPrincipal());
    }

    @PostMapping("/auth/register")
    public String register(@RequestBody RegisterRequest req) {

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword())); 
        user.setRole(Role.USER);
        user.setName(req.getName());
        userRepo.save(user);
        return "User registered";
    }
}
