package com.qf.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class MyController {

    /**
     *  支付成功返回成功页面
     * @return
     */
    @RequestMapping("success")
    public String getSuccess(){
        return "success";
    }

    /**
     * 跳转支付详情页面
     */

    @RequestMapping("pay")
    public String showPay(){
        return "orderConfirm";
    }

    @RequestMapping("doPay")
    public void doPay(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse,String oid) throws ServletException, IOException {
        /**
         *参数说明：
         * 1. 支付网关
         * 2.应用id
         * 3.商户应用私钥
         * 4.返回的数据类型： json
         * 5.字符集： utf-8
         * 6. 支付宝公钥  注意，是支付宝公钥，不是商户公钥
         * 7. 密钥生成方式： RSA2
         */

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016101100659765",
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCDXTFinPWAjg5451XkucoU4NTjlsRSUe2MoXfxyqYnhaaKfvpWZHuJVDU5P0kK7SvegJ+ow3/ff9rvhxO1yrDxQ5KH7n15HRBhXQEGqMT1yXD4tMaOEJlqoU8v4oT5r8AvBkU706Ymwkc6cgugfP4MDwmCr5uOENGi3/ClgAZv0WYXcQyPhJ12gq3Cn54e59uPL+zMaIYydLqidDW7gcpTCXfc68CIcyfcUoWIYQ5yADzb0IZh+B6MCS4f8W7LiF0iVrLx2bCbSo3NgIf4vRHLGychp8HcjlY5wAkrJ2p8jW8dQdzUqlegd4RV3x1lMGYSTeiXg18EoWbHk48mSLRNAgMBAAECggEALdfc/zQVfQqWRF0/HXY07rpTsSURWEUdKIHFEQ2OlKZQq26KwJGKhy9q75KFVBnWwGyUy1mGi6Hb7tFt8HhO7bOONH/nK9gXulKxo/hL3BazpSe5TyS91Xd74hMkItrRr9d5W9qN1mLgZqYcJqLYKa8pu4C7QYXfEv8hxS/7jxO9j0hBWzX0Pw5bsSDp3E0R+y9aTw+QozJD8iZ7Ue/8yXRLQvQ6shsQWZ7mTBGSRgkGs5lc9ZDm4z7VW4b+CAW+5xgVElth9pFMgV90yEf/wQv2DSMKdua3qJ1tljnSkAWF/ZyUOgPXczbJiQUhDk/xCw0YCY3Ce6BtBaalONZPwQKBgQC68kKxzM5BiWKWqIbVHtpWkApEJn1CgNJ/Y8o8crTrTw+q5KBCMlm4wYZXjdy3O1kVRLeFVtYajq9RriXFFU4eh945IuhZpqOJbXsOgWwOzleOJ/vVVVSwqymgnrEHnztd8VBifaYHkvyJssMiJkC7yfOUw8Ysg+9LVNcbtD2EHQKBgQCz4wrdaeyeYVlS73deMTEZfoLNnVuMBBKkriHkgUzYpEL/C6o0BVTxduGgcNSoy0XpNBTvR9ivCu53d2v9ABgSj1ViG3/NpimkHftapJWR2IjpEqSB3mVZlb0wzJVzIUjpMB+HUUBnY8+dHrg/wStVC9/W0GUBaojAkdLLRfKZ8QKBgQC4SyFCpFNkuipNuX+KU+sMUN0+B++OAu8AlICHDNGb84nPf6kY+QgIIgftAjXnEIOmeuKseUmCTs04qYQkgPdNE8YX73i9AJDRZvNb/mxnQDWCN5qE2aUusN3NsQN2LxxYZQ9IuPNXHikFGgxR+wHv3ovZSRJxSfM6AT452nqWZQKBgQCUbQLa/HpzmEl2qAXrH1Wu/nb5JxyRZA7p+JAYliONIcPx43MVp0yWTAwRMOgXOlo9+G5JnROKIs/unQfqYnh6X/AImj4StkbnlqF+Ag0zoOIexfYc/h7ME0Rejn/F1jHtznpFUeJ9LJryvpUybbXOX5TbD2o4B+o8UlYGyXlKUQKBgQCGFCvMMDLbz+6ZPsIAmIgSvGKh3G4CA8ssQGTF0Yh1bij20XpPlOJsgDcwBDtd2njTQXnFZ6yrFDbOdqeMKM6iedZ7S6j00dhq0FKE1goF229LJM038WXg3ZQDjrqMPZIjFBk0ZZV1m0D71aqPK5Mwcn1kq4OjkGQCNYpGxQkqcw==",
                "JSON",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAovePsqV6HCfcrhxQBIiBeVdSVigCY9zMsSTXV3B8nTB2M2YMPy9fOE+hVdDOTxNlW1uTXUneP1/JJKpCHLN4VT3zRYNDCehu2ow4yg6vv+6m9aiK0E0vl1BvWykLuwC8MfveqYazGXRGos165jgPFcyN9LIG4ztL6vrHH8etIaElu7AdeaQnBox3b+puWlmGOmiLq83MtLxcHZHCuw4yqZrZTNnOsZyqgWgp+xPXVfhUD7HsuQcXiv47FboSt6yaVQpmSXXYHo68Idr3mxYPM6X4pZyQwuLkoYBB4sJPmwE2525sJSWOP9nwQG3ypAT6+kLk5kYK1IurtpVP5g2m2wIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://ubh6cs.natappfree.cc/success");
        alipayRequest.setNotifyUrl("http://ubh6cs.natappfree.cc/notifyUrl");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+oid+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":2000000.88," +
                "    \"subject\":\"Iphone16 16G\"," +
                "    \"body\":\"Iphone16 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    @RequestMapping("notifyUrl")
    @ResponseBody
    public void notifyUrl(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        //需要将map中的String[]==>String
        Map<String, String> paramsMap = new HashMap<>(); //将异步通知中收到的所有参数都存放到 map 中
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String[] values = entry.getValue();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < values.length-1; i++) {
                sb.append(values[i]+",");
            }
            sb.append(values[values.length-1]);
            paramsMap.put(entry.getKey(),sb.toString());
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAovePsqV6HCfcrhxQBIiBeVdSVigCY9zMsSTXV3B8nTB2M2YMPy9fOE+hVdDOTxNlW1uTXUneP1/JJKpCHLN4VT3zRYNDCehu2ow4yg6vv+6m9aiK0E0vl1BvWykLuwC8MfveqYazGXRGos165jgPFcyN9LIG4ztL6vrHH8etIaElu7AdeaQnBox3b+puWlmGOmiLq83MtLxcHZHCuw4yqZrZTNnOsZyqgWgp+xPXVfhUD7HsuQcXiv47FboSt6yaVQpmSXXYHo68Idr3mxYPM6X4pZyQwuLkoYBB4sJPmwE2525sJSWOP9nwQG3ypAT6+kLk5kYK1IurtpVP5g2m2wIDAQAB",
                "utf-8",
                "RSA2"); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if(paramsMap.get("out_trade_no").equals("20200313105750004")&&
                    paramsMap.get("total_amount").equals("2000000.88")){
                System.out.println("金额正确，验签成功");//要去数据库中改变订单状态
                //TODO response.getWriter().write("json");

            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }


    }




}