package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.model.Author;
import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap(){
        return authorService.getAuthorMap();
    }

    @GetMapping("/authors")
    public String authorPage(){
        return "/authors/index";
    }

    @GetMapping("/authors/slug/{id:\\d+}")
    public String slugPage(@PathVariable Integer id, Model model) {
        Page<Book> books = bookService.getBookByAuthor(id,0,10);
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("authorBook", books.getContent());
        model.addAttribute("size", books.getTotalElements());
        return "/authors/slug";
    }

}
