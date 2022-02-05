package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.BookDto;
import com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.StorageService;
import com.example.MyBookShopApp.service.TagService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Api("Book Controller")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final StorageService storageService;
    private final TagService tagService;
    private final AuthorService authorService;

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks(){
        return bookService.getBookData(0,20).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks(){
        return bookService.getPopularBooks(0,20).getContent();
    }

    @GetMapping("/books/{slug}")
    public String slug(@PathVariable("slug") String slug, Model model){
        model.addAttribute("slugBook", bookService.getBookBySlug(slug));
        return "/books/slug";
    }

    @PostMapping("books/{slug}/img/save")
    public String saveBookImage(@RequestParam("file")MultipartFile file, @PathVariable("slug") String slug){
        String savePath = storageService.saveBookImage(file, slug);
        Book bookToUpdate = bookService.getBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookRepository.save(bookToUpdate);
        return ("redirect:/books/" + slug);
    }

    @GetMapping("books/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {

        Path path = storageService.getBookFilePath(hash);
        MediaType mediaType = storageService.getBookFileMime(hash);
        byte[] data = storageService.getBookFileByteArray(hash);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }

    @GetMapping(value = "/books/tag/{id}")
    @ResponseBody
    public BookDto getBookByTag(@PathVariable Integer id, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){
        return new BookDto(bookService.getBookByTag(id,offset,limit).getContent());
    }

    @GetMapping("/tags/{tagId:\\d+}")
    public String getTag(@PathVariable Integer tagId, Model model) {
        model.addAttribute("tagBooks", bookService.getBookByTag(tagId, 0, 10));
        model.addAttribute("tag", tagService.getTag(tagId));
        return "tags/index";
    }


    @GetMapping("books/recent")
    public String getRecent(Model model) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        model.addAttribute("recentBooks", bookService.getRecentBook(calendar.getTime(), new Date(), 0, 10));
        model.addAttribute("dateFrom", calendar.getTime());
        model.addAttribute("dateTo", new Date());
        return "/books/recent";
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");

    @SneakyThrows
    @GetMapping("/books/recent/page")
    @ResponseBody
    public ResponseEntity<BookDto> getNextSearchPage(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "from", defaultValue = "") String from,
            @RequestParam(value = "to", defaultValue = "") String to) {
        return ResponseEntity.ok(new BookDto(bookService.getRecentBook(dateFormat.parse(from),
                dateFormat.parse(to), offset, limit).getContent()));
    }

    @GetMapping("/books/popular")
    public String popularPage(){
        return "/books/popular";
    }

    @GetMapping("books/author/{id}")
    public String allBookByAuthor(@PathVariable Integer id, Model model){
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("authorBooks", bookService.getBookByAuthor(id, 0, 10).getContent());
        return "/books/author";
    }
}
