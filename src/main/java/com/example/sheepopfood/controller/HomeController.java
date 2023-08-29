package com.example.sheepopfood.controller;

import com.example.sheepopfood.model.User;
import com.example.sheepopfood.service.CategoryService;
import com.example.sheepopfood.service.ProductService;
import com.example.sheepopfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    /**
    @GetMapping
    public String showIndex(){
        return "login";
    }

//    @GetMapping("/cart")
//    public String showCart(){
//        return "cart";
//    }
    @GetMapping("/checkout")
    public String showCheckout(){
        return "checkout";
    }

    @GetMapping("/shop")
//    public String showShop(){
//        return "shop";
//    }
    **/

    @GetMapping("/home1")
    public String showIndex(){
        return "home";
    }
    @GetMapping("/shop")
    public ModelAndView showShop(){
        ModelAndView view = new ModelAndView("shop");
        User user = userService.getUser();
        view.addObject("id_user",user.getId());
        return view;
    }

}
