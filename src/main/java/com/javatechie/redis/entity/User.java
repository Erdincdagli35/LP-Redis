package com.javatechie.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class User implements Serializable {
    @Id
    private Long id;

    private String name;

    private String password;

    private String jwtToken;
}
