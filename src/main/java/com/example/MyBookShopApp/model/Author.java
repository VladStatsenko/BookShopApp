package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.model.link.Book2Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "bookshop",name = "author")
@Getter
@Setter
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "author")
    @JsonIgnore
    private List<Book2Author> books = new ArrayList<>();

}
