package com.backend.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();

System.out.println("PATH: " + path);

// ✅ STRICT skip BEFORE anything
if (path.equals("/auth/login") || path.equals("/auth/register")) {
    chain.doFilter(request, response);
    return;
}

        String token = null;

        // ✅ 2. Extract token from cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // ✅ 3. Validate token and set authentication
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                Claims claims = JwtUtil.validateToken(token);

                String email = claims.get("email", String.class);
                String role = claims.get("role", String.class);

                var userDetails = User.withUsername(email)
                        .password("") // password not needed here
                        .roles(role)
                        .build();

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // ✅ Set authentication in context
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
               System.out.println("Invalid JWT → ignoring");
              SecurityContextHolder.clearContext();
            }
        }

        // ✅ 4. Continue request
        chain.doFilter(request, response);
    }
}