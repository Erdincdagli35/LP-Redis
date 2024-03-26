package com.javatechie.redis.service;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserReturnType;
import com.javatechie.redis.respository.UserRepository;
import com.javatechie.redis.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserReturnType singUp(User user) {
        user.setJwtToken(generateToken(user.getName()));
        user = userRepository.save(user);

        UserReturnType userReturnType = userMapToUserReturnType(user);

        return userReturnType;
    }

    public UserReturnType userMapToUserReturnType(User user) {
        UserReturnType userReturnType = new UserReturnType();
        userReturnType.setName(user.getName());
        userReturnType.setToken(user.getJwtToken());
        return userReturnType;
    }

    public List<UserReturnType> usersMapToUserReturnType(List<User> users) {
        return users.stream()
                .map(user -> {
                    UserReturnType userReturnPojo = new UserReturnType();
                    userReturnPojo.setName(user.getName());
                    userReturnPojo.setToken(user.getJwtToken());
                    return userReturnPojo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserReturnType login(User user) {
        User savedUser = findUserById(user.getId());

        if (user.getPassword().equals(savedUser.getPassword()) &&
                savedUser.getJwtToken().equals(user.getJwtToken())) {
            UserReturnType userReturnType = userMapToUserReturnType(savedUser);
            return userReturnType;
        }

        return null;
    }


    @Override
    public UserReturnType changePassword(UserPasswordChangePojo userPasswordChangePojo) {
        User user = userRepository.findUserByName(userPasswordChangePojo.getName());
        user.setPassword(userPasswordChangePojo.getNewPassword());
        User savedUser = userRepository.save(user);
        return userMapToUserReturnType(savedUser);
    }

    @Override
    public List<UserReturnType> findAll() {
        List<User> users = userRepository.findAll();
        return usersMapToUserReturnType(users);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public String delete(Long id) {
        return userRepository.delete(id);
    }

    public String generateToken(String name) {
        return jwtUtil.generateToken(name);
    }
}
