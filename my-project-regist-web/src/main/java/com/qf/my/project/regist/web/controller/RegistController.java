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

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }

    @RequestMapping("/email/regist/{addr}/{password}")
    @ResponseBody
    public ResultBean emailRegist(@PathVariable("addr") String addr, @PathVariable("password") String password){
        //生成UUID
        String uuid = UUID.randomUUID().toString();
        //调用邮件服务发送邮件
        String url = String.format("http://email-service/email/send/%s/%s",uuid,addr);
        ResultBean resultBean = restTemplate.getForObject(url, ResultBean.class);
        //将uuid和addr存入redis ==0说命resultBean对象没有问题
        if (resultBean != null && resultBean.getErrno()==0){
            redisTemplate.opsForValue().set(RedisUtil.getRedisKey(RedisConstant.REGIST_EMAIL_URL_KEY_PRE, uuid), addr, 15, TimeUnit.MINUTES);
        }

        return ResultBean.success("web成功");
    }
}
