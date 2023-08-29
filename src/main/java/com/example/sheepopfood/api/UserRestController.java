package com.example.sheepopfood.api;

import com.example.sheepopfood.model.User;
import com.example.sheepopfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public ResponseEntity<?> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
