package com.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // authorize URLs
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/error", "/webjars/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                )
                // ✅ Form login (manual username/password)
                .formLogin(form -> form
                .loginPage("/login") // your custom login.html
                .defaultSuccessUrl("/home", true) // redirect after success
                .permitAll()
                )
                // ✅ Google OAuth2 login
                .oauth2Login(oauth -> oauth
                .loginPage("/login") // same page can show both options
                .defaultSuccessUrl("/home", true)
                )
                // ✅ Logout (works for both)
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                )
                // ✅ Disable CSRF for testing with APIs (optional)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // ✅ In-memory user store for manual login testing
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("anup")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }
}
