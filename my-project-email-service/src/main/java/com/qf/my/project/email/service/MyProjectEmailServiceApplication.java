package com.qf.my.project.email.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyProjectEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyProjectEmailServiceApplication.class, args);

    }


}
