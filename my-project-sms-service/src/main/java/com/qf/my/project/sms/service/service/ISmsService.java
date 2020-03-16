package com.qf.my.project.sms.service.service;

import com.qf.dto.ResultBean;

/**
 * Create by 10262
 * on 2020/3/13 8:58
 */
public interface ISmsService {

    public ResultBean sendSms(String phone,String suiji);

    public void check();
}
