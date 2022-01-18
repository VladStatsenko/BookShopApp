package com.example.MyBookShopApp.model.link;

import com.example.MyBookShopApp.model.Author;
import com.example.MyBookShopApp.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "bookshop",name = "book2author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book2Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sort_index", columnDefinition = "INT NOT NULL  DEFAULT 0")
    private int sortIndex;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
