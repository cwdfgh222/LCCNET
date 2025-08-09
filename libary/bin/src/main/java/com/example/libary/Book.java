package com.example.libary;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Lombok: 自動產生 getter, setter, toString, equals, hashCode
@Entity // JPA: 標示這個類別是一個實體，對應到資料庫的 Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
}
