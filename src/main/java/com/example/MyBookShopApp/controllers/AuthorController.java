package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap(){
        return authorService.getAuthorMap();
    }

    @GetMapping("/authors")
    public String authorPage(){
        return "/authors/index";
    }

}
