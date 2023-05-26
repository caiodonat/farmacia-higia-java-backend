package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/tt")
    public String sumTest() {
        return "Hello World";
    }
}