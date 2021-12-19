package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.model.link.Book2Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String photo;
    private String slug;
    private String name;
    private String description;

    @OneToMany(mappedBy = "author")
    private List<Book2Author> books = new ArrayList<>();

}
