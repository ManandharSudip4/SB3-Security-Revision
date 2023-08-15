package com.mstech.springsecurityrevision.service;

import com.mstech.springsecurityrevision.entity.UserEntity;
import java.util.Optional;
import org.springframework.stereotype.Service;

// this is actually a repository
@Service
public class UserService {

  private static final String EXISTING_EMAIL = "admin@mstech.com";
  private static final String ANOTHER_EMAIL = "user@mstech.com";

  public Optional<UserEntity> findByEmail(String email) {
    if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
      var user = new UserEntity();
      user.setId(1L);
      user.setEmail(EXISTING_EMAIL);
      user.setPassword(
        "$2a$12$M40PodlldfCeS/XfJ/0Bz.xs.Se/NygsBz9QAZbklxnUOvyC5otdm"
      ); // pass
      user.setRole("ROLE_ADMIN");
      return Optional.of(user);
    } else if (ANOTHER_EMAIL.equals(email)) {
      var user = new UserEntity();
      user.setId(10L);
      user.setEmail(ANOTHER_EMAIL);
      user.setPassword(
        "$2a$12$M40PodlldfCeS/XfJ/0Bz.xs.Se/NygsBz9QAZbklxnUOvyC5otdm"
      ); // pass
      user.setRole("ROLE_USER");
      return Optional.of(user);
    }

    return Optional.empty();
  }
}
