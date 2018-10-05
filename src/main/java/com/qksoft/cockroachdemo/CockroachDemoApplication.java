package com.qksoft.cockroachdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CockroachDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(CockroachDemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CockroachDemoApplication.class);
    }

}