package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.model.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Map<String, List<Author>> getAuthorMap(){
        return authorRepository.findAll().stream().collect(Collectors.groupingBy((Author a)->{return a.getName().substring(0,0);}));
    }
}
