package com.mstech.springsecurityrevision.controller;

import com.mstech.springsecurityrevision.security.UserPrincipal;
import com.mstech.springsecurityrevision.security.UserPrincipalAuthenticationToken;
import java.util.Arrays;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockSecurityContextFactory
  implements WithSecurityContextFactory<WithMockUser> {

  @Override
  public SecurityContext createSecurityContext(WithMockUser annotation) {
    var authorities = Arrays
      .stream(annotation.authorities())
      .map(SimpleGrantedAuthority::new)
      .toList();

    var prinicipal = UserPrincipal
      .builder()
      .userId(annotation.userId())
      .email("test@test.com")
      .authorities(authorities)
      .build();

    var context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(new UserPrincipalAuthenticationToken(prinicipal));

    return context;
  }
}
