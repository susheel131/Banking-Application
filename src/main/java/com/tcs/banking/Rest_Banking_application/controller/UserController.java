package com.tcs.banking.Rest_Banking_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcs.banking.Rest_Banking_application.entity.User;
import com.tcs.banking.Rest_Banking_application.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePageView() {
 	   return "home";
    }
    
    
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
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        if (attempts == null) attempts = 0;

        
        if (attempts ==3) {
        	session.setAttribute("loginAttempts", 0);
            model.addAttribute("error", "🚫 You entered incorrect password 3 times. Account Locked!");
            return "home";
        }

        
        User user = userService.findByEmail(email);

        if (user.getPassword().equals(password)) {

           
            session.setAttribute("loginAttempts", 0);

            model.addAttribute("name", user.getName());
            return "welcome";  
        }

        
        attempts++;
        session.setAttribute("loginAttempts", attempts);

        model.addAttribute("error", "❌ Invalid Credentials! Attempts Left: " + (3 - attempts));
        return "login";
    }

    
    
    
    
    
}