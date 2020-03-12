package com.qf.my.project.email.service.controller;

import com.qf.dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * Create by 10262
 * on 2020/3/11 14:09
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${activeServerUrl}")
    private String activeServerUrl;

    @RequestMapping("active/account/{uuid}")
    @ResponseBody
    public ResultBean activeAccount(@PathVariable("uuid") String uuid) {

        return ResultBean.success("注册成功");
    }

    @RequestMapping("/send/{uuid}/{addr}")
    @ResponseBody
    public ResultBean emailSend(@PathVariable String uuid, @PathVariable String addr) throws Exception {

        //带html格式的message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);
        mailMessage.setSubject("请激活您在本中心得账号");

        //读取模板内容
        Context context = new Context();
        context.setVariable("username",addr.substring(0,addr.lastIndexOf('@')));
        context.setVariable("url",activeServerUrl+uuid);
        String info = templateEngine.process("emailTemplate", context);
        mailMessage.setFrom("1026222344@qq.com");
        mailMessage.setText(info, true);
        mailMessage.setTo(addr);
        mailSender.send(message);

        return ResultBean.success("发送成功");
    }
}
