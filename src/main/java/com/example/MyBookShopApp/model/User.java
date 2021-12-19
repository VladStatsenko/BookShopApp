package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.model.file.FileDownload;
import com.example.MyBookShopApp.model.link.Book2User;
import com.example.MyBookShopApp.model.payments.BalanceTransaction;
import com.example.MyBookShopApp.model.review.BookReview;
import com.example.MyBookShopApp.model.review.BookReviewLike;
import com.example.MyBookShopApp.model.review.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(name = "reg_time",columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Book2User> books = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BookReview> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BookReviewLike> bookReviewLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserContact> userContacts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BalanceTransaction> balanceTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FileDownload> fileDownloads = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();
}
