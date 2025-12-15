package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SessionService sessionService;

    public HomeController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public String home() {
        // If logged in, redirect to projects
        if (!sessionService.isLoggedIn()) return "redirect:/projects";

        // If user is not logged in, redirect to login
        return "redirect:/login";
    }
}
