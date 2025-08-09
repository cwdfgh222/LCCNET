package com.example.libary.repository;

import com.example.libary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Spring Data JPA 會自動實現這個方法：依據書名模糊查詢
    List<Book> findByTitleContaining(String title);
}