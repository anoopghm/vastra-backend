package com.backend.controller;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.DTO.LoginRequest;
import com.backend.model.Account;
import com.backend.repository.AccountRepository;
import com.backend.util.JwtUtil;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountRepository accountRepo;
    private final PasswordEncoder encoder;

    public AuthController(AccountRepository accountRepo, PasswordEncoder encoder) {
        this.accountRepo = accountRepo;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest req) {

        Account user = accountRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String access = JwtUtil.generateAccessToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        String refresh = JwtUtil.generateRefreshToken(user.getId());

        return Map.of(
                "accessToken", access,
                "refreshToken", refresh
        );
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestHeader("Authorization") String header) {

        String token = header.substring(7);
        Claims claims = JwtUtil.validateToken(token);

        if (!"refresh".equals(claims.get("type"))) {
            throw new RuntimeException("Invalid refresh token");
        }

        Long userId = Long.parseLong(claims.getSubject());
        Account user = accountRepo.findById(userId).orElseThrow();

        String newAccess = JwtUtil.generateAccessToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        return Map.of("accessToken", newAccess);
    }
}
