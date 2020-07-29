package com.example.msgpush;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.*")
@MapperScan(basePackages = "com.example.msgpush.mapper")
public class MsgpushApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MsgpushApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
