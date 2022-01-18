package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.model.Genre;
import com.example.MyBookShopApp.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> getAllGenre(){
        return genreRepository.findAll();
    }

    public Genre getById(int id){
        return genreRepository.getById(id);
    }
}
