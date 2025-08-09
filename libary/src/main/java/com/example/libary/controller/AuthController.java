package com.example.libary.controller;

import com.example.libary.model.User;
import com.example.libary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional; // 新增這個 import

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    // 透過建構子注入 UserService
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    // 登入端點 (已修改為實際驗證邏輯)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        System.out.println("User '" + username + "' attempting to log in.");
        
        // 呼叫 UserService 的驗證方法來進行實際驗證
        Optional<User> authenticatedUser = userService.authenticate(username, password);

        if (authenticatedUser.isPresent()) {
            // 登入成功
            System.out.println("User '" + username + "' logged in successfully.");
            // 在真實應用中，這裡應該生成一個 JWT 權杖
            String fakeToken = "fake-jwt-token-for-" + username;
            return ResponseEntity.ok(Map.of("message", "登入成功！", "token", fakeToken));
        } else {
            // 登入失敗
            System.out.println("Login failed for user '" + username + "'.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("message", "使用者名稱或密碼錯誤。"));
        }
    }


    // 註冊端點 (保持不變)
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User userInfo) {
        System.out.println("Registering new user: " + userInfo.getUsername());
        
        try {
            userService.registerNewUser(userInfo);
            return ResponseEntity.ok(Map.of("message", "註冊成功！正在為您跳轉..."));
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("message", "註冊失敗：" + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "註冊失敗：伺服器內部錯誤。"));
        }
    }
}