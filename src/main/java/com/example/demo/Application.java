package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class Application {
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${app.environment:UNKNOWN}")
    private String environment;

    public static void main(String[] args) {
        System.out.println("=== MICROSERVICE1 STARTING ===");
        System.out.println("DEBUG: ABCDEFGHIJKLMNOPQRSTUVWXYZ - Full alphabet!");
        System.out.println("DEBUG: Extra letters ABC123XYZ on DEV branch!");
        System.out.println("DEBUG: Application is starting with enhanced logging...");
        System.out.println("DEBUG: Current timestamp: " + java.time.LocalDateTime.now());
        System.out.println("DEBUG: Java version: " + System.getProperty("java.version"));
        System.out.println("DEBUG: NEW DEPLOYMENT TEST - MICROSERVICE1 DEV v7.001!");
        System.out.println("DEBUG: TESTING ARGOCD AUTO DEPLOYMENT FEATURE!");
        System.out.println("DEBUG: ADDITIONAL LOGGING FOR CI/CD VERIFICATION - FORCE TRIGGER!");
        System.out.println("=== MICROSERVICE1 INITIALIZATION COMPLETE ===");
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String hello() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String response = "Hello from Microservice 111 - DEV环境自动部署测试 v7.0016661 - ArgoCD Test - Current Time: " + now.format(formatter) + " (ENV: " + environment.toUpperCase() + ")";
        
        logger.info("🚀 Main endpoint accessed - Environment: {}, Timestamp: {}", environment.toUpperCase(),  now.format(formatter));
        logger.info("📊 Request processed successfully for main endpoint");
        
        return response;
    }
    
    @GetMapping("/health")
    public String health() {
        logger.info("🏥 Health endpoint accessed - Environment: {}", environment);
        logger.info("✅ Health check completed successfully");
        return "{\"status\":\"UP\",\"version\":\"6.0\",\"environment\":\"" + environment + "\",\"timestamp\":\"" + LocalDateTime.now() + "\"}";
    }
}
// Test CI/CD - Sun Aug 10 01:27:14 PM CST 2025
