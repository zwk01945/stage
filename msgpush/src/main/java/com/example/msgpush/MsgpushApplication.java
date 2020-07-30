package com.example.msgpush;

import com.example.msgpush.socket.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.*")
@MapperScan(basePackages = "com.example.msgpush.mapper")
public class MsgpushApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MsgpushApplication.class, args);
            new NettyServer(5000).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
