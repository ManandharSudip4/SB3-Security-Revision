package com.mstech.springsecurityrevision.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mstech.springsecurityrevision.security.UserPrincipal;

@RestController
public class HomeController {
    @GetMapping("/")
    public String greetings(){
        return "Hola Mundo!";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal prinicipal){
        return "Successful Login as " + prinicipal.getEmail() + " User ID: " + prinicipal.getUserId() ;
    }
}
