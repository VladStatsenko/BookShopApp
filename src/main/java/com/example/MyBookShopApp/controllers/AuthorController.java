package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.model.Author;
import com.example.MyBookShopApp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
