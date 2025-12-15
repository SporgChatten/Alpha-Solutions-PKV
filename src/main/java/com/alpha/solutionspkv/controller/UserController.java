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

    @GetMapping
    public String viewAllUsers(Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", sessionService.getCurrentUser());
        return "users/list";
    }

    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable int id, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("currentUser", sessionService.getCurrentUser());
            return "users/view";
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/edit";
        }
        return "redirect:/users";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        if (!sessionService.isLoggedIn()) return "redirect:/login";

        // Check if user is deleting themselves
        User currentUser = sessionService.getCurrentUser();
        boolean isDeletingSelf = (currentUser != null && currentUser.getId() == id);

        userService.deleteUser(id);

        // If user deleted themselves, logout and redirect to login
        if (isDeletingSelf) {
            sessionService.logout();
            return "redirect:/login";
        }

        return "redirect:/users";
    }
}
