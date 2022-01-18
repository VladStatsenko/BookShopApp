package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.model.Genre;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenresController {

    private final GenreService genreService;
    private final BookService bookService;

    @ModelAttribute("genre")
    public List<Genre> recommendedBooks(){
        return genreService.getAllGenre();
    }

    @GetMapping("/")
    public String genresPage(){
//        model.addAttribute("bookData", bookService.getBooksData());
        return "/genres/index";
    }

    @GetMapping("/slug/{id:\\d+}")
    public String slugPage(@PathVariable Integer id, Model model) {
        Page<Book> books = bookService.getBookByGenre(id,0,10);
        model.addAttribute("genre", genreService.getById(id));
        model.addAttribute("genreBook", books.getContent());
        model.addAttribute("size", books.getTotalElements());
        return "/genres/slug";
    }
}
