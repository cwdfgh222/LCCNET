// UserService.java 檔案

package com.example.libary.service;

import com.example.libary.model.User;
import com.example.libary.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 建構子
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 註冊新使用者的方法 (已修正)
    public User registerNewUser(User user) {
        // 1. 檢查使用者名稱是否已存在
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("使用者名稱 '" + user.getUsername() + "' 已存在！");
        }
        // 2. 對密碼進行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 3. 將使用者物件存入資料庫，並回傳儲存後的物件
        return userRepository.save(user); 
    }

    // 新增這段方法來解決錯誤
    public Optional<User> authenticate(String username, String rawPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // passwordEncoder.matches() 會比對使用者輸入的原始密碼與資料庫中加密後的密碼
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return userOptional; // 密碼匹配，回傳使用者物件
            }
        }
        return Optional.empty(); // 使用者不存在或密碼不匹配
    }
}