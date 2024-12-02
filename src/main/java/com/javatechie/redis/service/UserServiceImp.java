package com.javatechie.redis.service;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.mapping.UserMapping;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserReturnType;
import com.javatechie.redis.respository.UserRepository;
import com.javatechie.redis.security.JwtUtil;
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
    JwtUtil jwtUtil;

    private UserReturnType userReturnType;

    @Override
    public UserReturnType signUp(User user) {
        User newUser = new User();
        user.setId(newUser.getId());

        if (!userValidation.isThereAnyUserWithSameId(user.getId())) {
            userReturnType = userMapping.setReturnValuesAsFailed(user);
        } else {
            user.setToken(generateToken(user.getName()));
            userReturnType = userMapping.setReturnValuesAsSuccess(user);

            userRepository.save(user);
        }
        return userReturnType;
    }

    @Override
    public UserReturnType login(User user) {
        return userValidation.loginCheckByPasswordAndToken(user) ?
                userMapping.setReturnValuesAsSuccess(user) : userMapping.setReturnValuesAsFailed(user);
    }


    @Override
    public UserReturnType changePassword(UserPasswordChangePojo userPasswordChangePojo) {
        User user = userRepository.findUserByName(userPasswordChangePojo.getName());
        User savedUser;

        if (!userValidation.checkOldPasswordByNewPassword(userPasswordChangePojo) ||
                !userValidation.checkNewPasswordByNewPasswordConfirm(userPasswordChangePojo)) {
            userReturnType = userMapping.setReturnValuesAsFailed(user);
        } else {
            user.setPassword(userPasswordChangePojo.getNewPassword());
            savedUser = userRepository.save(user);
            userReturnType = userMapping.setReturnValuesAsSuccess(savedUser);
        }

        return userReturnType;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public String delete(String id) {
        return userRepository.delete(id);
    }

    public String generateToken(String name) {
        return jwtUtil.generateToken(name);
    }
}
