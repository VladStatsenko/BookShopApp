package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final JdbcTemplate jdbcTemplate;

    public List<Author> getAuthorData(){

        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors ", (ResultSet rs, int rowNum)->{
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setAuthor(rs.getString("author"));
            return author;
        });
        return new ArrayList<>(authors);
    }
}
