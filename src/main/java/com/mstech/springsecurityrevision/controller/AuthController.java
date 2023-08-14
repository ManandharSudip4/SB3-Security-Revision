package com.mstech.springsecurityrevision.controller;

import com.mstech.springsecurityrevision.model.LoginRequest;
import com.mstech.springsecurityrevision.model.LoginResponse;
import com.mstech.springsecurityrevision.security.JwtIssuer;
import com.mstech.springsecurityrevision.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtIssuer jwtIssuer;

  private final AuthenticationManager authenticationManager;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    var authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    var principal = (UserPrincipal) authentication.getPrincipal();

    var roles = principal
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .toList();

    var token = jwtIssuer.issue(
      principal.getUserId(),
      principal.getEmail(),
      roles
    );
    return LoginResponse.builder().accessToken(token).build();
  }
}
