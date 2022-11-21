package com.notificationsystem.fourqu.emailSender;

import com.notificationsystem.fourqu.event.RegisteredEvent;
import com.notificationsystem.fourqu.event.ShopRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
@Slf4j
@Component
public class WelcomeEmailSender extends EmailSender {

    public WelcomeEmailSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        super(mailSender, templateEngine);
    }
    public void sent (RegisteredEvent event){
        String subject = "Welcome to QU PLUS";
        String personal = "QU PLUS";
        String template = "welcome-template";

        try {
            Context ctx = new Context();
            ctx.setVariable("name",event.getName());
            MimeMessage mimeMessage = super.createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                    subject,template,ctx);
            System.out.println("WelcomeEmail sending...");
            mailSender.send(mimeMessage);
            System.out.println("WelcomeEmail has sent.");

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
        }
    }

    public void sent (ShopRegisterEvent event){
        String subject = "Welcome to QU PLUS Shop Service";
        String personal = "QU PLUS";
        String template = "shopRegistered-template";

        try {
            Context ctx = new Context();
            ctx.setVariable("name",event.getName());
            ctx.setVariable("shopName",event.getShopName());
            MimeMessage mimeMessage = super.createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                    subject,template,ctx);
            System.out.println("WelcomeEmail sending...");
            mailSender.send(mimeMessage);
            System.out.println("WelcomeEmail has sent.");

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
        }
    }

}
