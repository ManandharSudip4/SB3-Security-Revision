package com.mstech.springsecurityrevision.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String greetings(){
        return "Hola Mundo!";
    }

    @GetMapping("/secured")
    public String secured(){
        return "Successful Login";
    }
}
