package com.alpha.solutionspkv.controller;

import com.alpha.solutionspkv.model.User;
import com.alpha.solutionspkv.service.SessionService;
import com.alpha.solutionspkv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    // Check if user is logged in for all requests
    private boolean checkLogin() {
        return sessionService.isLoggedIn();
    }

    // View all users
    @GetMapping
    public String viewAllUsers(Model model) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", sessionService.getCurrentUser());
        return "users/list";
    }

    // Show form.html for new user
    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        model.addAttribute("user", new User());
        return "users/form.html";
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable int id, Model model) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/view";
        }
        return "redirect:/users";
    }

    // Show edit form.html
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/edit";
        }
        return "redirect:/users";
    }

    // Update user
    @PostMapping("/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    // Delete user
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        if (!checkLogin()) {
            return "redirect:/login";
        }
        userService.deleteUser(id);
        return "redirect:/users";
    }
}