package com.notificationsystem.fourqu.emailSender;

import com.notificationsystem.fourqu.event.ActivityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class ActivitySender extends EmailSender {

    public ActivitySender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        super(mailSender, templateEngine);
    }
    public void sent(ActivityEvent event){

        String subject = "Successfully logged in to QU PLUS";
        String personal = "QU PLUS";
        String template = "activity-template.html";

        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(event.getTimeStamp());

        try {
            //fill Payment information
            Context ctx = new Context();
            ctx.setVariable("AccountID",event.getAccountId());
            ctx.setVariable("timeStamp",date);

            //create email content
            MimeMessage mimeMessage = super.createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                    subject,template,ctx);

            //send email
            System.out.println("ActivityEmail sending...");
            mailSender.send(mimeMessage);
            System.out.println("ActivityEmail has sent.");

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
        }
    }
}
