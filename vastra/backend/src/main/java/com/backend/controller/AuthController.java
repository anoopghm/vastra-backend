package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request,
                                   HttpServletResponse response) {

        // 1. Authenticate user (simplified)
        if (!request.getUsername().equals("admin")
                || !request.getPassword().equals("password")) {
            return ResponseEntity.status(401).build();
        }

        // 2. Generate access token
        String accessToken = JwtUtil.generateToken(request.getUsername());

        // 3. Store token in HTTP-only cookie
        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(15 * 60); // 15 minutes
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}
