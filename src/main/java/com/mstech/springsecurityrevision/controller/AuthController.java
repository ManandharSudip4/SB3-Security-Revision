package com.mstech.springsecurityrevision.controller;

import com.mstech.springsecurityrevision.model.LoginRequest;
import com.mstech.springsecurityrevision.model.LoginResponse;
import com.mstech.springsecurityrevision.security.JwtIssuer;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtIssuer jwtIssuer;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
    return LoginResponse.builder().accessToken(token).build();
  }
}
