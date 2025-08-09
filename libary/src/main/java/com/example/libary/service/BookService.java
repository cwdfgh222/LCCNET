package com.example.libary.service;

import com.example.libary.model.Book;
import com.example.libary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return bookRepository.findByTitleContaining(keyword);
        }
        return bookRepository.findAll();
    }

    public String triggerCrawlerUpdate() {
        // 在真實世界中，這裡會執行一個命令來啟動 Python 爬蟲腳本
        // 例如：ProcessBuilder processBuilder = new ProcessBuilder("python", "crawler/main.py");
        // processBuilder.start();
        System.out.println("INFO: 收到更新請求，正在模擬觸發 Python 爬蟲...");
        // 這裡僅返回一個訊息，表示請求已收到
        return "Book update process has been triggered.";
    }
}
