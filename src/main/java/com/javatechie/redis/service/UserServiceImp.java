package com.javatechie.redis.service;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.mapping.UserMapping;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserPojoReturnType;
import com.javatechie.redis.pojo.UserReturnType;
import com.javatechie.redis.respository.UserRepository;
import com.javatechie.redis.utils.TokenService;
import com.javatechie.redis.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidation userValidation;

    @Autowired
    UserMapping userMapping;

    @Autowired
    TokenService tokenService;

    private UserReturnType userReturnType;

    @Override
    public UserReturnType signUp(User user) {
        user.setToken(tokenService.generateToken(user.getName()));
        userRepository.save(user);
        return userMapping.setReturnValuesAsSuccess(user);
    }

    @Override
    public UserReturnType login(User user) {
        return userMapping.setReturnValuesAsSuccess(user);
    }


    @Override
    public UserPojoReturnType changePassword(UserPasswordChangePojo userPasswordChangePojo) {
        User user = userRepository.findUserByName(userPasswordChangePojo.getName());
        user.setPassword(userPasswordChangePojo.getNewPassword());
        userRepository.save(user);
        return userMapping.setReturnPojoValuesAsSuccess(userPasswordChangePojo);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public String delete(String name) {
        return userRepository.delete(name);
    }
}
