package com.example.MyBookShopApp.service;

import liquibase.util.file.FilenameUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    @Value("${upload.path}")
    String uploadPath;

    @SneakyThrows
    public String saveBookImage(MultipartFile file, String slug){
        String resourceURI = null;

        if(!file.isEmpty()){
            if(!new File(uploadPath).exists()){
                Files.createDirectories(Paths.get(uploadPath));
            }

            String fileName = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            Path path = Paths.get(uploadPath,fileName);
            resourceURI = "/booksPhoto/" + fileName;
            file.transferTo(path);
        }
        return resourceURI;
    }
}
