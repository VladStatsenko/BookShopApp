package com.example.MyBookShopApp.model.review;

import com.example.MyBookShopApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewLike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short value;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private BookReview bookReview;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
