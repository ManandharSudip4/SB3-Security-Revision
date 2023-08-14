package com.mstech.springsecurityrevision.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

  private String email;
  private String password;

}
