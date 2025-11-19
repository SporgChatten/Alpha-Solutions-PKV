package com.alpha.solutionspkv.Controller;

import com.alpha.solutionspkv.Model.Role;
import com.alpha.solutionspkv.Model.User;
import com.alpha.solutionspkv.Service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "Login";
    }

    // Login handling
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.findUserByUserName(username);
        if (user != null && user.getPassword().equals(password)) {

            session.setAttribute("loggedUser", user);
            return "redirect:/projects";
        } else {
            model.addAttribute("error", "Ugyldigt brugernavn eller kodeord");
            return "Login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "Register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {


        if (userService.findUserByUserName(username) != null) {
            model.addAttribute("error", "Username already taken");
            return "Register";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userService.create(user);

        model.addAttribute("success", "User registered successfully!");
        return "Register";
    }
}
