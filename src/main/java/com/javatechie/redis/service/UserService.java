package com.javatechie.redis.service;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserPojoReturnType;
import com.javatechie.redis.pojo.UserReturnType;

import java.util.List;

public interface UserService {

    UserReturnType signUp(User user);

    UserReturnType login(User user);

    List<User> findAll();

    User findUserByName(String name);

    String delete(String name);

    UserPojoReturnType changePassword(UserPasswordChangePojo userPasswordChangePojo);
}