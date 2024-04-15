package com.javatechie.redis.mapping;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserReturnType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapping {

    public UserReturnType setReturnValuesAsSuccess(User user) {
        UserReturnType userReturnType = userMapToUserReturnType(user);
        userReturnType.setReturnCode("200");
        userReturnType.setReturnMessage("Successfully.");
        return userReturnType;
    }

    public List<UserReturnType> setReturnValuesAsSuccessForUsers(List<User> users) {
        List<UserReturnType> usersReturnTypeList = usersMapToUserReturnType(users);
        List<UserReturnType> newUserReturnTypeList = new ArrayList<>();

        for (UserReturnType returnType : usersReturnTypeList) {
            returnType.setReturnCode("200");
            returnType.setReturnMessage("Successfully.");

            newUserReturnTypeList.add(returnType);
        }

        return newUserReturnTypeList;
    }

    public UserReturnType setReturnValuesAsFailed(User user) {
        UserReturnType userReturnType = userMapToUserReturnType(user);
        userReturnType.setReturnCode("404");
        userReturnType.setReturnMessage("The user saved before");
        return userReturnType;
    }

    public UserReturnType userMapToUserReturnType(User user) {
        UserReturnType userReturnType = new UserReturnType();
        userReturnType.setName(user.getName());
        userReturnType.setToken(user.getToken());
        return userReturnType;
    }

    public List<UserReturnType> usersMapToUserReturnType(List<User> users) {
        return users.stream()
                .map(user -> {
                    UserReturnType userReturnPojo = new UserReturnType();
                    userReturnPojo.setName(user.getName());
                    userReturnPojo.setToken(user.getToken());
                    return userReturnPojo;
                })
                .collect(Collectors.toList());
    }
}
