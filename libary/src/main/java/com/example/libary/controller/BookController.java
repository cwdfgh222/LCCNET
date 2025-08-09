package com.example.libary.controller;

import com.example.libary.model.Book;
import com.example.libary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 查詢書目 API，可依關鍵字查詢
    @GetMapping
    public List<Book> searchBooks(@RequestParam(required = false) String keyword) {
        return bookService.searchBooks(keyword);
    }

    // 觸發更新書目 API
    @PostMapping("/update")
    public ResponseEntity<?> updateBooks() {
        String message = bookService.triggerCrawlerUpdate();
        return ResponseEntity.ok(Map.of("message", message));

    }
}