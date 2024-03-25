package com.javatechie.redis.service;

import com.javatechie.redis.entity.User;

import java.util.List;

public interface UserService {

    User save(User product);

    List<User> findAll();

    User findUserById(Long id);

    String deleteUser(Long id);
}
