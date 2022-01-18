package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.SearchDto;
import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GeneralController {

    private final BookService bookService;

    @ModelAttribute("searchDto")
    public SearchDto searchDto(){
        return new SearchDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResult(){
        return new ArrayList<>();
    }

    @GetMapping("/cart")
    public String getCart(){
        return "/cart";
    }

    @GetMapping("/signin")
    public String signIn(){
        return "/signin";
    }

    @GetMapping(value = {"/search/","/search/{searchWord}"})
    public String search(@PathVariable(value = "searchWord", required = false) SearchDto searchDto, Model model){
        model.addAttribute("searchDto", searchDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearch(searchDto.getExample(),0,20).getContent());
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
