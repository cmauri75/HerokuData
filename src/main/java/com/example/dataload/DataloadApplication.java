package com.example.dataload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DataloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataloadApplication.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Data loaded successfully";
    }
}
