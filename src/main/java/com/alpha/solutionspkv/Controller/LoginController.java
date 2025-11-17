package com.alpha.solutionspkv.Controller;

import com.alpha.solutionspkv.Model.User;
import com.alpha.solutionspkv.Service.UserService;
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
        return "login"; // Thymeleaf template
    }

    // Login handling
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        // Find bruger via username
        User user = userService.findUserByUserName(username);

        if (user != null && user.getPassword().equals(password)) {
            // Login succes – send bruger til dashboard
            model.addAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            // Login fejl – vis fejlmeddelelse
            model.addAttribute("error", "Ugyldigt brugernavn eller kodeord");
            return "login";
        }
    }
}
