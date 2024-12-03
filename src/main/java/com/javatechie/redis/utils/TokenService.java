package com.javatechie.redis.utils;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    @Autowired
    JwtUtil jwtUtil;

    public String generateToken(String name) {
        return jwtUtil.generateToken(name);
    }

    public void userTokenSetForLoginProcess(User user, User savedUser){
        user.setToken(savedUser.getToken());
    }
}
