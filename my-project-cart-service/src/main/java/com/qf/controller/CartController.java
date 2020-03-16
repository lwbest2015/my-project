package com.qf.controller;

import com.qf.constant.RedisConstant;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Create by 10262
 * on 2020/3/14 17:01
 */

//判断用户是否已经登录
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/show")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("/islogin")
    @ResponseBody
    public String isLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String key = cookie.getValue();

            String value = redisTemplate.opsForValue().get(RedisUtil.getRedisKey(RedisConstant.USER_LOGIN,key));

            if (value != null) {
                return "已经登录";
            }
        }
        return "您还未登录请登录";
    }
}
