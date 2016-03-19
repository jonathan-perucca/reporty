package com.github.perucca.mailer.service;

import com.github.perucca.mailer.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

import static javax.mail.Message.RecipientType.TO;

@Service
public class MailerService {

    private final JavaMailSender mailSender;
    private final VelocityService velocityService;

    @Autowired
    public MailerService(JavaMailSender javaMailSender,
                         VelocityService velocityService) {
        this.mailSender = javaMailSender;
        this.velocityService = velocityService;
    }

    public void sendMail(Template template) {
        Map<String, Object> properties = template.getModel();
        String receiverMail = template.getReceiverMail();
        String mailContent = velocityService.getMailContent(properties);

        mailSender.send(mimeMessage -> {
            mimeMessage.setFrom("under.64@gmail.com");
            mimeMessage.setRecipients(TO, receiverMail);
            mimeMessage.setText(mailContent);
        });
    }
}
