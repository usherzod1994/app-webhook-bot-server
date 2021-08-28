package com.example.appwebhookbotserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppWebhookBotServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWebhookBotServerApplication.class, args);
    }

}
