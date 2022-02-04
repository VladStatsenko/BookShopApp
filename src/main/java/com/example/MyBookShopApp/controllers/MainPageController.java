package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BookDto;
import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.model.Tag;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks(){
        return bookService.getNewBook(0,10).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks(){
        return bookService.getPopularBooks(0,10).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<Book> newBooks(){
        return bookService.getNewBook(0,10).getContent();
    }

    @ModelAttribute("tags")
    public List<Tag> allTags(){
        return tagService.getAllTag();
    }


    @GetMapping("/")
    public String mainPage(){
        return "/index";
    }

    @GetMapping(value = "/recommended")
    @ResponseBody
    public ResponseEntity<BookDto> recommendedBooks(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){

        return ResponseEntity.ok(new BookDto(bookService.getBookData(offset,limit).getContent()));
    }

    @GetMapping(value = "/popular")
    @ResponseBody
    public ResponseEntity<BookDto> popularBooks(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){
        return ResponseEntity.ok(new BookDto(bookService.getPopularBooks(offset,limit).getContent()));
    }

    @GetMapping(value = "/recent")
    @ResponseBody
    public ResponseEntity<BookDto> newBook(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){
        return ResponseEntity.ok(new BookDto(bookService.getNewBook(offset,limit).getContent()));
    }

    @GetMapping(value = "/tags")
    public List<Tag> getTag(){
        return tagService.getAllTag();
    }




}
