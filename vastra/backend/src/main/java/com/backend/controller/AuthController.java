package com.backend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.DTO.LoginRequest;
import com.backend.model.Account;
import com.backend.repository.AccountRepository;
import com.backend.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpServletResponse response) {
        var optional = accountRepo.findByEmail(req.getEmail());
        if (optional.isEmpty() || !encoder.matches(req.getPassword(), optional.get().getPassword())) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        Account user = optional.get();
        String access = JwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        String refresh = JwtUtil.generateRefreshToken(user.getId());

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", access)
                .httpOnly(true).secure(false).sameSite("Lax").path("/").maxAge(15 * 60).build();
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refresh)
                .httpOnly(true).secure(false).sameSite("Lax").path("/auth/refresh").maxAge(7 * 24 * 60 * 60).build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return ResponseEntity.ok(user);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true).secure(false).sameSite("Lax").path("/").maxAge(0).build();
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true).secure(false).sameSite("Lax").path("/auth/refresh").maxAge(0).build();
        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        return ResponseEntity.ok().build();
    }
}
