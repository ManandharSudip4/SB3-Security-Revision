package com.mstech.springsecurityrevision.service;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.mstech.springsecurityrevision.entity.UserEntity;

// this is actually a repository
@Service
public class UserService {
    
    private static final String EXISTING_EMAIL = "manandharsudip8@gmail.com";

    public Optional<UserEntity> findByEmail(String email){
        
        if (!EXISTING_EMAIL.equalsIgnoreCase(email)) return Optional.empty();

        var user = new UserEntity();
        user.setId(1L);
        user.setEmail(EXISTING_EMAIL);
        user.setPassword("$2a$12$M40PodlldfCeS/XfJ/0Bz.xs.Se/NygsBz9QAZbklxnUOvyC5otdm"); // pass
        user.setRole("ROLE_ADMIN");
        return Optional.of(user);
    }
}
