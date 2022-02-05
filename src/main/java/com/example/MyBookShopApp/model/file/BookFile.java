package com.example.MyBookShopApp.model.file;

import com.example.MyBookShopApp.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_file", schema = "bookshop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hash;
    private String path;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BookFileType type;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
