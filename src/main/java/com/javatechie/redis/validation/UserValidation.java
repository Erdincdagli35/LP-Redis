package com.javatechie.redis.validation;

import com.javatechie.redis.config.UserTokenConfiguration;
import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTokenConfiguration userTokenConfiguration;

    public boolean isThereAnyUserWithSameId(String id) {
        return userRepository.findUserById(id) == null;
    }

    public boolean loginCheckByPasswordAndToken(User user) {
        User savedUser = userRepository.findUserByName(user.getName());

        if (savedUser == null){
            return false;
        }

        userTokenConfiguration.userTokenSetForLoginProcess(user, savedUser);

        return user.getPassword().equals(savedUser.getPassword()) &&
                user.getToken().equals(savedUser.getToken());
    }

    public boolean checkOldPasswordByNewPassword(UserPasswordChangePojo userPasswordChangePojo) {
        return !(userPasswordChangePojo.getOldPassword().equals(userPasswordChangePojo.getNewPassword()));
    }

    public boolean checkNewPasswordByNewPasswordConfirm(UserPasswordChangePojo userPasswordChangePojo) {
        return userPasswordChangePojo.getNewPassword().equals(userPasswordChangePojo.getNewPasswordConfirm());
    }
}