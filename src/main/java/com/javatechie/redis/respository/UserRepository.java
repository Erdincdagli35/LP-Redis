package com.javatechie.redis.respository;

import com.javatechie.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    public static final String HASH_KEY = "User";
    @Autowired
    private RedisTemplate template;

    public User save(User user) {
        template.opsForHash().put(HASH_KEY, user.getId(), user);
        return user;
    }

    public List<User> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public User findUserById(String id) {
        return (User) template.opsForHash().get(HASH_KEY, id);
    }

    public User findUserByName(String name) {
        Map<Object, Object> allUsers = template.opsForHash().entries(HASH_KEY);
        for (Map.Entry<Object, Object> entry : allUsers.entrySet()) {
            User user = (User) entry.getValue();
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public String delete(String id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "user removed !!";
    }
}
