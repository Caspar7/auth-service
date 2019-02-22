package com.dyc.auth.service;

import com.dyc.auth.dto.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //启用缓存
    @Cacheable(value = "user", key = "'user_'+#userName")
    public User getUser(String userName) {
        System.out.println(userName + "进入实现类获取数据！");
        User user = new User();
        user.setUserName("caspar");
        user.setPassword("123");
        return user;
    }
}
