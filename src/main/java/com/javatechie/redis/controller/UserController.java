package com.javatechie.redis.controller;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.mapping.UserMapping;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserPojoReturnType;
import com.javatechie.redis.pojo.UserReturnType;
import com.javatechie.redis.respository.UserRepository;
import com.javatechie.redis.service.UserService;
import com.javatechie.redis.utils.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidationService userValidationService;

    @Autowired
    private UserMapping userMapping;

    @PostMapping("/signUp")
    public ResponseEntity<UserReturnType> signUp(@RequestBody User user) {
        return userValidationService.validateSameUser(user)
                ? ResponseEntity.ok(userService.signUp(user))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(userMapping.setReturnValuesAsFailed(user));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserPojoReturnType> changePassword(@RequestBody UserPasswordChangePojo userPasswordChangePojo) {
        return (!userValidationService.validateSameUser(userPasswordChangePojo))
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(userMapping.setReturnPojoValuesAsFailed(userPasswordChangePojo))
                : (!userValidationService.validateNewPasswordCheck(userPasswordChangePojo) || !userValidationService.validateNewPasswordConfirm(userPasswordChangePojo))
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userMapping.setReturnPojoValuesAsFailed(userPasswordChangePojo))
                : ResponseEntity.ok(userService.changePassword(userPasswordChangePojo));
    }


    @PostMapping("/login")
    public ResponseEntity<UserReturnType> login(@RequestBody User user) {
        return userValidationService.validateLogin(user)
                ? ResponseEntity.ok(userService.login(user))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(userMapping.setReturnValuesAsFailed(user));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{name}")
    public User findByName(@PathVariable String name) {
        return userService.findUserByName(name);
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable String name) {
        return userService.delete(name);
    }

}
