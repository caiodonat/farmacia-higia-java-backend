package com.farmaciahigia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    // @GetMapping("/docs")
    // public String sumTest() {
    //     return "Hello World";
    // }
}