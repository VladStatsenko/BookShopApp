package com.example.MyBookShopApp.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {

    private Integer id;
    private String author;
    private String title;
    private Integer priceOld;
    private Integer price;

}
