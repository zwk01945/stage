package com.example.graphql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.*")
@MapperScan(basePackages = "com.example.graphql.mapper")
public class GraphqlApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GraphqlApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
