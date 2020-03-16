package com.qf.my.project.regist.service.service;

import com.qf.dto.ResultBean;

/**
 * Create by 10262
 * on 2020/3/12 17:54
 */
public interface IRegistService {

    public ResultBean insertUser(String addr,String password);
    public void insertUserByPhone(String phone,String password);

    boolean isTure(String phone,String password);
}
