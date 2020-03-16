package com.qf.my.project.regist.service.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.my.project.regist.service.service.IRegistService;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by 10262
 * on 2020/3/11 14:16
 */
@Controller
@RequestMapping("/user")
public class RegistController {

    @Autowired
    private IRegistService iRegistService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //判断登录信息
    @RequestMapping("/istrue/{phone}/{password}")
    @ResponseBody
    public boolean isTrue(@PathVariable String phone, @PathVariable String password) {

        boolean ture = iRegistService.isTure(phone, password);
        System.out.println(1234568789);
        return true;
    }


    //向数据库中插入数据
    @RequestMapping("/regist/{addr}/{password}")
    @ResponseBody
    public ResultBean regist(@PathVariable String addr, @PathVariable String password) {
        iRegistService.insertUser(addr, password);
        return ResultBean.success("注册mysql成功");
    }

    @RequestMapping("/check/{phone}/{password}/{suiji}")
    @ResponseBody
    public String check(@PathVariable("phone") String phone, @PathVariable("password") String password, @PathVariable("suiji") String suiji) {
        System.out.println("值" + suiji + phone);
        String key = "regist:sms:" + phone;
        System.out.println(RedisUtil.getRedisKey(RedisConstant.REGIST_SMS, "13201605310"));
        String s = redisTemplate.opsForValue().get(RedisUtil.getRedisKey(RedisConstant.REGIST_SMS, "13201605310"));
        System.out.println("value" + s);
        if ((s.compareTo(suiji)) == 0) {
            iRegistService.insertUserByPhone(phone, password);
        } else {
            return "您的验证码无效，请重新输入";
        }
        return "成功";
    }

}
