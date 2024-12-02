package com.javatechie.redis.controller;

import com.javatechie.redis.entity.User;
import com.javatechie.redis.pojo.UserPasswordChangePojo;
import com.javatechie.redis.pojo.UserReturnType;
import com.javatechie.redis.respository.UserRepository;
import com.javatechie.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserReturnType> signUp(@RequestBody User user) {
        return ResponseEntity.ok(userService.signUp(user));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserReturnType> changePassword(@RequestBody UserPasswordChangePojo userPasswordChangePojo) {
        return ResponseEntity.ok(userService.changePassword(userPasswordChangePojo));
    }


    @PostMapping("/login")
    public ResponseEntity<UserReturnType> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return userService.delete(id);
    }

}
