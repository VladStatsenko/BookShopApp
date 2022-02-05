package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable pageable);

    @Query("SELECT b FROM Book b")
    Page<Book> findAllBook(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isBestseller=1")
    Page<Book> findPopularBook(Pageable pageable);

    Page<Book> findPageOfBooksByPubDateBetweenOrderByPubDateDesc(Date dateTo , Date dateFrom, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.tagList t WHERE t.id = :id")
    Page<Book> getBookByTag(@Param("id") int id, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.authors t WHERE t.id = :id")
    Page<Book> getBookByAuthor(@Param("id") int id, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.id = :id")
    Page<Book> getBookByGenre(@Param("id") int id, Pageable pageable);

    Book findBookBySlug(String slug);

    List<Book> findBooksBySlugIn(String[] slug);
}
