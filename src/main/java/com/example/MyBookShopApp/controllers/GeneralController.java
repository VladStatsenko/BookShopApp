package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping("/cart")
    public String getCart(){
        return "/cart";
    }

    @GetMapping("/signin")
    public String signIn(){
        return "/signin";
    }

    @GetMapping("/search/")
    public String search(){
        return "/search/index";
    }

    @GetMapping("/documents/")
    public String documents(){
        return "/documents/index";
    }

    @GetMapping("/about")
    public String about(){
        return "/about";
    }

    @GetMapping("/faq")
    public String faq(){
        return "/faq";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "/contacts";
    }

    @GetMapping("/postponed")
    public String postponed(){
        return "/postponed";
    }
}
