package com.javatechie.redis.pojo;

import lombok.Data;

@Data
public class UserReturnType {
    public String name;
    public String token;
    public String returnCode;
    public String returnMessage;
}
