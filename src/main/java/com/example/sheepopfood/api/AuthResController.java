package com.example.sheepopfood.api;


import com.example.sheepopfood.service.AuthService;
import com.example.sheepopfood.service.User.request.RegisterRequest;
import com.example.sheepopfood.service.User.request.UserSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthResController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void create(@RequestBody RegisterRequest request){
        authService.register(request);
    }
}
