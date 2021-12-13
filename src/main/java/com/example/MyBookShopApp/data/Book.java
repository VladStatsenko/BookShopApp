package com.example.MyBookShopApp.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private Integer id;
    private String author;
    private String title;
    private Integer priceOld;
    private Integer price;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priceOld=" + priceOld +
                ", price=" + price +
                '}';
    }

}
