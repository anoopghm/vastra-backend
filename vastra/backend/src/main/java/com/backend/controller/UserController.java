package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(authentication.getName());
    }
}
