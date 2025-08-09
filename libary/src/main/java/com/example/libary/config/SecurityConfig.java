package com.example.libary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 停用 CSRF 保護，因為我們將使用 JWT 權杖
            .csrf(AbstractHttpConfigurer::disable)
            // 配置授權規則
            .authorizeHttpRequests(authorize -> authorize
                // 允許對 /api/auth/register 的 POST 請求，以及 /register.html 的頁面存取
                .requestMatchers("/api/auth/register", "/register.html").permitAll()
                // 允許對 /api/auth/login 的 POST 請求，以及 /login.html 的頁面存取
                .requestMatchers("/api/auth/login", "/login.html").permitAll()
                // 允許對靜態資源（如 CSS, JS）的存取
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                // 所有其他請求都需要身份驗證
                .anyRequest().authenticated()
            );

        return http.build();
    }
}