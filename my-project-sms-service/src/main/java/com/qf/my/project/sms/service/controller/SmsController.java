package com.qf.my.project.sms.service.controller;

import com.qf.dto.ResultBean;
import com.qf.my.project.sms.service.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by 10262
 * on 2020/3/12 19:32
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private ISmsService iSmsService;

    @RequestMapping("/send/{phone}/{suiji}")
    public ResultBean sendSms(@PathVariable("phone") String phone, @PathVariable("suiji") String suiji){

        iSmsService.sendSms(phone,suiji);
        return ResultBean.success("success");
    }
}
