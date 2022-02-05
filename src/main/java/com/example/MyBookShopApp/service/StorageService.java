package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.model.file.BookFile;
import com.example.MyBookShopApp.repository.BookFileRepository;
import liquibase.util.file.FilenameUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class StorageService {

    @Value("${upload.path}")
    String uploadPath;

    @Value("${download.path}")
    String downloadPath;

    private final BookFileRepository bookFileRepository;

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

    public Path getBookFilePath(String hash) {
        BookFile bookFile = bookFileRepository.findBookFileByHash(hash);
        return Paths.get(bookFile.getPath());
    }

    public MediaType getBookFileMime(String hash) {
        BookFile bookFile = bookFileRepository.findBookFileByHash(hash);
        String mimeType =
                URLConnection.guessContentTypeFromName(Paths.get(bookFile.getPath()).getFileName().toString());
        if (mimeType != null){
            return MediaType.parseMediaType(mimeType);
        }else{
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public byte[] getBookFileByteArray(String hash) throws IOException {
        BookFile bookFile = bookFileRepository.findBookFileByHash(hash);
        Path path = Paths.get(downloadPath, bookFile.getPath());
        return Files.readAllBytes(path);
    }
}
