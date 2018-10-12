package com.qksoft.cockroachdemo;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class CockroachDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException {
        SpringApplication springApplication = new SpringApplication(CockroachDemoApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        Properties properties = new Properties();
        InputStream inputStream = CockroachDemoApplication.class.getClassLoader().getResourceAsStream("inner-conifg.properties");
        properties.load(inputStream);
        springApplication.setDefaultProperties(properties);

        Map<String,Object> defaultProps = new HashMap<>();
        defaultProps.put("spring.jpa.show-sql",false);
        springApplication.setDefaultProperties(defaultProps);
        springApplication.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CockroachDemoApplication.class);
    }
}




