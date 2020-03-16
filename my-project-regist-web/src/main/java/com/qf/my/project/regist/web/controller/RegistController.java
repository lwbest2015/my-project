package com.qf.my.project.regist.web.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create by 10262
 * on 2020/3/11 14:34
 */
@Controller
@RequestMapping("/user")

public class RegistController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;//redisTemplate不需要配置config 加入依赖直接引入

    @RequestMapping("/toRegister")
    public String userRegist() {
        return "register";
    }


    /*
     *输入手机号，密码登录
     */
    @RequestMapping("/login/{phone}/{password}")
    @ResponseBody
    public String login(@PathVariable String phone, @PathVariable String password, HttpServletResponse resp) {

        Boolean object = restTemplate.getForObject(String.format("http://regist-service/user/istrue/%s/%s", phone, password), Boolean.class);
        if (object) {
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(CookeConstant.USER_LOGIN, uuid);
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);
            String redisKey = RedisUtil.getRedisKey(RedisConstant.USER_LOGIN, uuid);
            redisTemplate.opsForValue().set(redisKey, phone, 30, TimeUnit.MINUTES);
            return "成功";
        }
        return "失败";
    }

    //短信注册阶段/手机号 密码  操作数
    @RequestMapping("/check/{phone}/{password}/{suiji}")
    @ResponseBody
    public ResultBean check(@PathVariable("phone") String phone, @PathVariable("password") String password, @PathVariable("suiji") String suiji) {
        System.out.println("====" + password + "---" + phone + "+++" + suiji);
        restTemplate.getForObject(String.format("http://regist-service/user/check/%s/%s/%s", phone, password, suiji), ResultBean.class
        );
        return ResultBean.success("短信注册阶段成功");
    }

    //短信注册,发送阶段
    @RequestMapping("/send/sms/{phone}")
    @ResponseBody
    public ResultBean sendSms(@PathVariable("phone") String phone) {
        //生成UUID
        Integer integer = (int) ((Math.random() + 1) * 1000);
        String suiji = integer.toString();
        restTemplate.getForObject(String.format("http://sms-service/sms/send/%s/%s", phone, suiji), ResultBean.class);

        redisTemplate.opsForValue().set(RedisUtil.getRedisKey(RedisConstant.REGIST_SMS, "phone"), "suiji", 10, TimeUnit.MINUTES);
        return ResultBean.success("信息发送,已经注入缓存");
    }

    //邮箱注册
    @RequestMapping("/email/regist/{addr}/{password}")
    @ResponseBody
    public ResultBean emailRegist(@PathVariable("addr") String addr, @PathVariable("password") String password) {
        //生成UUID
        String uuid = UUID.randomUUID().toString();
        //调用邮件服务发送邮件
        String url = String.format("http://email-service/email/send/%s/%s", uuid, addr);
        ResultBean resultBean = restTemplate.getForObject(url, ResultBean.class);
        //将uuid和addr存入redis ==0resultBean对象没有问题
        if (resultBean != null && resultBean.getErrno() == 0) {
            //存入redis
            redisTemplate.opsForValue().set(RedisUtil.getRedisKey(RedisConstant.REGIST_EMAIL_URL_KEY_PRE, uuid), addr, 15, TimeUnit.MINUTES);
            //存入数据库 addr 和 password
            restTemplate.getForObject(String.format("http://regist-service/user/regist/%s/%s", addr, password), ResultBean.class);
        }


        return ResultBean.success("web成功");
    }
}
