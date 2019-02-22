package com.dyc.auth;


import com.dyc.auth.dto.User;
import com.dyc.auth.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Test
    //直接使用redisTemplate存取字符串
    public void setAndGet() {
        redisTemplate.opsForValue().set("test:set", "testValue1");
        String s = (String)redisTemplate.opsForValue().get("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NTA4NTgzMzcsImV4cCI6MTU1MDg1ODM0Nywic3ViIjoiOTUyNyIsImlzcyI6ImNhc3BhciIsInVzZXJJZCI6IjE5ODIiLCJ1c2VyTmFtZSI6ImNhc3BhciIsInBob25lIjoiMTg5ODg4ODg4ODgiLCJhZ2UiOjM1LCJzZXgiOiLnlLcifQ.1OZWqk60vaFQQ2xINWO7GWfiIVC9lemfQ_bKlyrMCxA");
        System.out.print("s="+s);
        Assert.assertEquals("testValue1", redisTemplate.opsForValue().get("test:set"));
    }

    @Test
    //直接使用redisTemplate存取对象
    public void setAndGetAUser() {
        User user = new User();
        user.setUserName("caspar");
        user.setPassword("123");

        redisTemplate.opsForValue().set("test:setUser", user);
        Assert.assertEquals(user.getUserName(), ((User) redisTemplate.opsForValue().get("test:setUser")).getUserName());
    }

    @Test
    //使用Redis缓存对象，getUser只会被调用一次
    public void testCache() {
        User user;
        user = userService.getUser("dyc");
        user = userService.getUser("dyc");
        user = userService.getUser("dyc");
    }
}