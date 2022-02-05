package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.model.file.BookFile;
import com.example.MyBookShopApp.model.file.FileDownload;
import com.example.MyBookShopApp.model.link.Book2Author;
import com.example.MyBookShopApp.model.link.Book2Genre;
import com.example.MyBookShopApp.model.link.Book2User;
import com.example.MyBookShopApp.model.payments.BalanceTransaction;
import com.example.MyBookShopApp.model.review.BookReview;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "bookshop",name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pub_date")
    private Date pubDate;
    @Column(name = "is_bestseller")
    private int isBestseller;
    private String slug;
    private String title;
    private String image;
    private String description;
    private int price;
    private int discount;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "book")
    @JsonIgnore
    private List<Book2Author> authors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "book")
    @JsonIgnore
    private List<Book2Genre> genres = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "book")
    @JsonIgnore
    private List<Book2User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "book")
    @JsonIgnore
    private List<BookReview> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BalanceTransaction> balanceTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<FileDownload> fileDownloads = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookFile> bookFiles = new ArrayList<>();

    @ManyToMany(mappedBy = "bookList")
    @JsonIgnore
    private List<Tag> tagList = new ArrayList<>();
}
