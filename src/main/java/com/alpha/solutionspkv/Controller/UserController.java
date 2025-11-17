package com.alpha.solutionspkv.Controller;

import com.alpha.solutionspkv.Model.Role;
import com.alpha.solutionspkv.Model.User;
import com.alpha.solutionspkv.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String ShowAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "AllUsers";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") int userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "findUserById";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values()); // send enum til dropdown
        return "user-edit"; // thymeleaf HTML fil
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users"; // redirect til user list
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        User user = new User(); // tomt objekt til formen
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values()); // dropdown med roller

        return "user-add";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.create(user); // Opretter bruger i DB
        return "redirect:/users"; // tilbage til brugerlisten
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int userId) {
        userService.delete(userId);
        return "redirect:/users";
    }


}


