package com.qf.my.project.regist.service.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.my.project.regist.service.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by 10262
 * on 2020/3/12 17:55
 */
@Service
public class IRegistServiceImpl implements IRegistService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public ResultBean insertUser(String addr, String password) {
        System.out.println("password=" + password + "++++++" + addr);
        TUser tUser = new TUser();
        tUser.setEmail(addr);
        tUser.setPassword(password);
        tUserMapper.insert(tUser);
        return ResultBean.success("添加用户到数据库成功");
    }

    @Override
    public void insertUserByPhone(String phone, String password) {
        TUser tUser = new TUser();
        tUser.setPhone(phone);
        tUser.setPassword(password);
        tUserMapper.insert(tUser);
    }

    @Override
    public boolean isTure(String phone, String password) {

        TUser tUser = tUserMapper.selectFromUserByPhoneNumber(phone);
        String password1 = tUser.getPassword();
        if (password1==password) {
            return true;
        } else {
            return false;
        }
    }
}
