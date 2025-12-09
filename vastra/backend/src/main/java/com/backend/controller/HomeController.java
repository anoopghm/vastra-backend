package com.backend.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/login")
    public String home(Principal principal) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User user) {
        if (user != null) {
            model.addAttribute("name", user.getAttribute("name"));
        }
        return "home";
        // String name = user.getAttribute("name");
        // return "Hello, " + name + "! <a href=\"/logout\">Logout</a>";
    }

}
