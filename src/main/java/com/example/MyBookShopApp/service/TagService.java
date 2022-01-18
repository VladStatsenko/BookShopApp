package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.model.Tag;
import com.example.MyBookShopApp.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> getAllTag(){
        return tagRepository.findAll();
    }

    public Tag getTag(int id){
        return tagRepository.getById(id);
    }


}
