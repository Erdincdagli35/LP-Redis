package com.javatechie.redis.config;

import com.javatechie.redis.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserTokenConfiguration {

    public void userTokenSetForLoginProcess(User user, User savedUser){
        user.setToken(savedUser.getToken());
    }
}
