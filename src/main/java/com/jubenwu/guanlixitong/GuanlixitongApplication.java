package com.jubenwu.guanlixitong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jubenwu.guanlixitong")
public class GuanlixitongApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuanlixitongApplication.class, args);
    }

}
