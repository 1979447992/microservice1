package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController
public class Application {

    @Value("${app.environment:UNKNOWN}")
    private String environment;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String hello() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Hello from Microservice 1 - SIT环境自动部署测试 v2.0 - Current Time: " + now.format(formatter) + " (ENV: " + environment.toUpperCase() + ")";
    }
    
    @GetMapping("/health")
    public String health() {
        return "{\"status\":\"UP\",\"version\":\"2.0\",\"environment\":\"" + environment + "\",\"timestamp\":\"" + LocalDateTime.now() + "\"}";
    }
}
