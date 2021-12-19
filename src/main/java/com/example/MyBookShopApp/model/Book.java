package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.model.file.FileDownload;
import com.example.MyBookShopApp.model.link.Book2Author;
import com.example.MyBookShopApp.model.link.Book2Genre;
import com.example.MyBookShopApp.model.link.Book2User;
import com.example.MyBookShopApp.model.payments.BalanceTransaction;
import com.example.MyBookShopApp.model.review.BookReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pub_date")
    private LocalDateTime pubDate;
    @Column(name = "is_bestseller")
    private int isBestseller;
    private String slug;
    private String title;
    private String image;
    private String description;
    private int price;
    private int discount;

    @OneToMany(mappedBy = "book")
    private List<Book2Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Book2Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Book2User> users = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<BookReview> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<BalanceTransaction> balanceTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<FileDownload> fileDownloads = new ArrayList<>();
}
