package com.example.MyBookShopApp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "bookshop",name = "tag")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int amount;

    @ManyToMany
    @JoinTable(name = "book2tag", joinColumns = @JoinColumn(name = "tag_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> bookList = new ArrayList<>();
}
