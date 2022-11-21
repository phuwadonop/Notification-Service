package com.notificationsystem.fourqu.emailSender;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public abstract class EmailSender {
    protected JavaMailSender mailSender;
    protected TemplateEngine templateEngine;
    public EmailSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    protected MimeMessage createEmailContent(String sourceEmail, String personal, String destEmail,
                                             String subject, String contentTemplate, Context ctx)
            throws MessagingException, UnsupportedEncodingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        message.setSubject(subject);
        message.setFrom(new InternetAddress(sourceEmail,personal));
        message.setTo(destEmail);
        String content = templateEngine.process(contentTemplate,ctx);
        message.setText(content,true);
        return mimeMessage;
    }
}
