package com.example.MyBookShopApp.dto;

import com.example.MyBookShopApp.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {

    private Integer count;
    private List<Book> books;

    public BookDto(List<Book> books) {
        this.books = books;
        this.count = books.size();

    }
}
