package com.example.MyBookShopApp.model.link;

import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book2genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book2Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
