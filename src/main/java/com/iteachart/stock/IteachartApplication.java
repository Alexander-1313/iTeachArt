package com.iteachart.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class IteachartApplication {

    public static void main(String[] args) {
        SpringApplication.run(IteachartApplication.class, args);
    }

}
