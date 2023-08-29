package com.example.sheepopfood.controller;

import com.example.sheepopfood.model.User;
import com.example.sheepopfood.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class BillController {
    private final UserService userService;
    @GetMapping
    public ModelAndView showCart(){
        ModelAndView view = new ModelAndView("cart");
        User user = userService.getUser();
        view.addObject("id_user",user.getId());
        return view;
    }
}
