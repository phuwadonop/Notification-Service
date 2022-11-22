package com.notificationsystem.fourqu.emailSender;

import com.notificationsystem.fourqu.event.OtpEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.AuthenticationFailedException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class OtpSender extends EmailSender {


    public OtpSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        super(mailSender, templateEngine);
    }
    public void sent(OtpEvent event) throws InterruptedException {
        String subject = "Verification code";
        String personal = "QU PLUS";
        String template = "otp-template.html";

        try {
            Context ctx = new Context();
            ctx.setVariable("otp",event.getOtp());
            MimeMessage mimeMessage = super.createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                                        subject,template,ctx);
            System.out.println("OTP sending...");
            mailSender.send(mimeMessage);
            System.out.println("OTP has sent.");
            throw new AuthenticationFailedException();

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
            if (e.getClass().getName() == "javax.mail.AuthenticationFailedException"){
                Thread.sleep(60000);
                sent(event);
            }
        }
    }
}
//    public void sent(OtpEmailEvent event){
//        try {
//            Context ctx = new Context();
//            ctx.setVariable("otp",event.getOtp());
//
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");
//            message.setSubject("Verification code");
//            message.setFrom(new InternetAddress(event.getSourceEmail(),"4QU"));
//            message.setTo(event.getDestEmail());
//            String content = templateEngine.process("otp-template.html",ctx);
//            message.setText(content,true);
//            System.out.println("OTP sending...");
//            mailSender.send(mimeMessage);
//            System.out.println("OTP has sent.");
//
//        } catch (Exception e) {
//            System.out.printf("Error: %s\n",e.getMessage());
//        }
//    }



