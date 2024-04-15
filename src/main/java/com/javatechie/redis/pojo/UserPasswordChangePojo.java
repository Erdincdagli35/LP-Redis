package com.javatechie.redis.pojo;

import lombok.Data;

@Data
public class UserPasswordChangePojo {
    public String id;
    public String oldPassword;
    public String newPassword;
    public String newPasswordConfirm;
}
