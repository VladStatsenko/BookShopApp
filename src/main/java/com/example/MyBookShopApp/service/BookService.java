package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> getBookData(Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.findAll(page);
    }

    public Page<Book> getPageOfSearch(String searchWord, Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.findBookByTitleContaining(searchWord,page);
    }

    public Page<Book> getPopularBooks(Integer offset,Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.findPopularBook(page);
    }

    public Page<Book> getRecentBook(Date dateTo, Date dateFrom, Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.findPageOfBooksByPubDateBetweenOrderByPubDateDesc(dateTo,dateFrom,page);
    }

    public Page<Book> getNewBook(Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit, Sort.by("pubDate").descending());
        return bookRepository.findAll(page);
    }

    public Page<Book> getBookByTag(int id,Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.getBookByTag(id,page);
    }

    public Page<Book> getBookByAuthor(int id,Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.getBookByAuthor(id,page);
    }

    public Page<Book> getBookByGenre(int id,Integer offset, Integer limit){
        Pageable page = PageRequest.of(offset,limit);
        return bookRepository.getBookByGenre(id,page);
    }
}
