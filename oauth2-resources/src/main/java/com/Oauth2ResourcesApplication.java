package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Oauth2ResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourcesApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

}
