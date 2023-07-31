package com.farmaciahigia.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HomeController {

    @Operation(summary = "Create a new Customer", tags = {}, hidden = true)
    @RequestMapping("/")
    RedirectView home() {
        return new RedirectView("/docs");
    }
}