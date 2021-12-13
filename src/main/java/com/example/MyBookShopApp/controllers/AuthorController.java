package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/")
    public String authorPage(Model model){
        model.addAttribute("authorData", authorService.getAuthorData());
        return "/authors/index";
    }

    @GetMapping("/slug")
    public String authorSlugPage(){
//        model.addAttribute("bookData", bookService.getBooksData());
        return "/authors/slug";
    }
}
