package com.iteachart.load;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.iteachart.model"})
public class LoadApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadApplication.class, args);
    }

}
