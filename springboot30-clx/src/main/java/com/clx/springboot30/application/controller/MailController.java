package com.clx.springboot30.application.controller;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送简单邮件
     * http://localhost:3100/sendSimpleMail?to=437330745@qq.com&subject=测试简单邮件主题&content=测试简单邮件内容
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @GetMapping("/sendSimpleMail")
    public String sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            return "Send Mail Success!";
        } catch (Exception e) {
            return "Send Mail Failed!";
        }
    }

    /**
     * 发送HTML邮件
     * http://localhost:3100/sendHtmlMail?to=437330745@qq.com&subject=测试HTML邮件主题&content=测试HTML邮件内容
     *
     * @param to
     * @param subject
     * @return
     */
    @GetMapping("/sendHtmlMail")
    public String sendHtmlMail(String to, String subject) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            // 获取thymeleaf的html模板
            Context context = new Context();
            String emailContent = templateEngine.process("mailTemplate.html", context); //指定模板路径
            messageHelper.setText(emailContent, true);

            mailSender.send(message);
            return "Send HTML Mail Success!";
        } catch (Exception e) {
            return "Send HTML Mail Failed!";
        }
    }

    /**
     * 发送带附件的邮件
     * http://localhost:3100/sendAttachmentMail?to=437330745@qq.com&subject=测试带附件的邮件主题&content=测试带附件的邮件内容
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @GetMapping("/sendAttachmentMail")
    public String sendAttachmentMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            // 获取附件
            FileSystemResource file = new FileSystemResource("src/main/resources/static/image/test.jpg");
            messageHelper.addAttachment("test.jpg", file);

            mailSender.send(message);
            return "Send Attachment Mail Success!";
        } catch (Exception e) {
            return "Send Attachment Mail Failed!";
        }
    }
}
