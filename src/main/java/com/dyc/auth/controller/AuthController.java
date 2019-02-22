package com.dyc.auth.controller;

import com.dyc.auth.dto.User;
import com.dyc.auth.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    JwtService jwtService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public String loginByPwd(@RequestBody User user) {
        //if access pass
        String jwtToken = jwtService.createJWT("1982","caspar");

        //将鉴权token放到redis 过期时间5分钟
        redisTemplate.opsForValue().set(jwtToken, jwtToken,5,TimeUnit.MINUTES);
        return jwtService.createJWT("1982","caspar");
    }
}
