package com.javatechie.redis.service;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserReturnType;

import java.util.List;

public interface UserService {

    UserReturnType singUp(User user);

    UserReturnType login(User user);

    List<UserReturnType> findAll();

    User findUserById(Long id);

    String delete(Long id);

    UserReturnType changePassword(UserPasswordChangePojo userPasswordChangePojo);
}
