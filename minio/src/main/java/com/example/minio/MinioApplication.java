package com.example.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.AbstractDataSourceInitializer;

@SpringBootApplication(scanBasePackages = "com.example.*")
public class MinioApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MinioApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
