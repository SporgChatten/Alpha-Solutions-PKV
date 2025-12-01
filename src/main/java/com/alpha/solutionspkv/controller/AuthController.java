package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.model.User;
import com.alpha.solutionspkv.service.SessionService;
import com.alpha.solutionspkv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final SessionService sessionService;

    public AuthController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // If already logged in, redirect to projects
        if (sessionService.isLoggedIn()) return "redirect:/projects";

        model.addAttribute("user", new User());
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute User user, Model model) {
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());

        if (authenticatedUser != null) {
            // Login successful
            sessionService.setCurrentUser(authenticatedUser);
            return "redirect:/projects";
        } else {
            // Login failed
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("user", new User());
            return "auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        sessionService.logout();
        return "redirect:/login";
    }
}