package com.mstech.springsecurityrevision.controller;

import com.mstech.springsecurityrevision.model.LoginRequest;
import com.mstech.springsecurityrevision.model.LoginResponse;
import com.mstech.springsecurityrevision.security.JwtIssuer;
import com.mstech.springsecurityrevision.security.UserPrincipal;
import com.mstech.springsecurityrevision.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.attemptLogin(request.getEmail(), request.getPassword());
  }
}
