package com.javatechie.redis.utils;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserValidation userValidation;

    @Autowired
    public UserValidationService(UserValidation userValidation) {
        this.userValidation = userValidation;
    }

    public boolean validateLogin(User user) {
        return userValidation.loginCheckByPasswordAndToken(user);
    }

    public boolean validateSameUser(User user){
        return userValidation.isThereAnyUserWithSameName(user.getName());
    }

    public boolean validateSameUser(UserPasswordChangePojo userPasswordChangePojo){
        return userValidation.isThereAnyUserWithSameName(userPasswordChangePojo.getName());
    }

    public boolean validateNewPasswordCheck(UserPasswordChangePojo userPasswordChangePojo){
        return userValidation.checkOldPasswordByNewPassword(userPasswordChangePojo);
    }

    public boolean validateNewPasswordConfirm(UserPasswordChangePojo userPasswordChangePojo) {
        return userValidation.checkNewPasswordByNewPasswordConfirm(userPasswordChangePojo);
    }
}
