package com.qf.my.project.sms.service.service.impl;

import com.qf.dto.ResultBean;
import com.qf.my.project.sms.service.service.ISmsService;
import com.qf.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by 10262
 * on 2020/3/13 8:59
 */
@Service
public class ISmsServiceImpl implements ISmsService {
    @Override
    public ResultBean sendSms(String phone,String suiji) {

        String host = "https://34sms.market.alicloudapi.com";
        String path = "/SmsSend";
        String method = "POST";
        String appcode = "c2d8ff2839024894bf3a09e3696afe6c";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("Mobiles", phone);
        querys.put("Tags", suiji);
        querys.put("TempId", "M633864F59");
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * 下载https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBean.success("发送短信成功");
    }

    @Override
    public void check() {

    }
}
