package com.javatechie.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@RedisHash("User")
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private String password;
    private String token;

    public User() {
        this.id = UUID.randomUUID().toString();
    }
}
