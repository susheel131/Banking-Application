package com.tcs.banking.Rest_Banking_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcs.banking.Rest_Banking_application.entity.User;
import com.tcs.banking.Rest_Banking_application.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        userService.register(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful! An account has been created for you.");
        return "login";  
    }
    
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Displays login.html after registration
    }
    
    @PostMapping("/login")
    public String login(String email, String password, Model model) {
        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("name", user.getName());
            return "welcome";  // after successful login -> open welcome page
        } else {
            model.addAttribute("error", "Invalid Credentials!");
            return "login";
        }
    }
    
    
    
    
    
}