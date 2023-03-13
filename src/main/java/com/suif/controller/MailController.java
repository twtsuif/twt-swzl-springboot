package com.suif.controller;

import com.suif.utils.Result;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;

@Controller
@RequestMapping("/api/mail")
@Data
@ConfigurationProperties(prefix = "suif.mail")
public class MailController {

    @Resource
    JavaMailSender javaMailSender;

    private String sendFrom;
    private String sendTo;
    private String subject;

    @PostMapping
    public Result sendMail(@RequestParam String content){
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(sendFrom);
            mail.setTo(sendTo);
            mail.setSubject(subject);
            mail.setText(content);

            javaMailSender.send(mail);
            return Result.success("反馈成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            return Result.fail("反馈失败");
        }
    }
}

