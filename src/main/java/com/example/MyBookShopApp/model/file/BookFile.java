package com.example.MyBookShopApp.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_file_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hash;
    private String path;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BookFileType type;
}
