package com.qf.my.project.sms.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyProjectSmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyProjectSmsServiceApplication.class, args);

    }


}
