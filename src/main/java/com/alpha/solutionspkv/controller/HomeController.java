package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/")
    public String home() {
        // If logged in, redirect to projects
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        // If user is not logged in, redirect to login
        return "redirect:/login";
    }
}