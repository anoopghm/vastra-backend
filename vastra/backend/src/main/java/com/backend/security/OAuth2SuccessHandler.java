package com.backend.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.backend.model.Account;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.repository.AccountRepository;
import com.backend.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AccountRepository accountRepo;

    public OAuth2SuccessHandler(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Account account = accountRepo.findByEmail(email)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(email);
                    user.setName(name);
                    user.setRole(Role.USER);
                    user.setPassword("");
                    return accountRepo.save(user);
                });

        String accessToken = JwtUtil.generateAccessToken(
                account.getId(),
                account.getEmail(),
                account.getRole().name()
        );

        String refreshToken = JwtUtil.generateRefreshToken(account.getId());

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(15 * 60)
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/auth/refresh")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        response.sendRedirect("http://localhost:5173/");
    }
}
