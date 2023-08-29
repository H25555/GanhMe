package com.example.sheepopfood.controller;

import com.example.sheepopfood.service.AuthService;
import com.example.sheepopfood.service.User.request.LoginRequest;
import com.example.sheepopfood.service.User.request.RegisterRequest;
import com.example.sheepopfood.service.User.request.UserSaveRequest;
import com.example.sheepopfood.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@AllArgsConstructor
@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showlogin(Model model) {
        LoginRequest user = new LoginRequest();
        UserDetails userDetails = getCurrentUser();
        model.addAttribute("user", user);
        return "login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserSaveRequest user = new UserSaveRequest();
        model.addAttribute("user", user);
        return "login";
    }


    @GetMapping("/logout")
    public String showLogout(Model model){
        LoginRequest user = new LoginRequest();
        return "login";
    }
    @PostMapping("/register")
    public String registration(@ModelAttribute RegisterRequest request){
        authService.register(request);
        return "redirect:/login";
    }
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }

        return null;
    }
}



