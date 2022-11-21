package com.notificationsystem.fourqu.emailSender;

import com.notificationsystem.fourqu.event.PaymentEvent;
import com.notificationsystem.fourqu.event.PaymentRcEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class PaymentSender extends EmailSender{
    public PaymentSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        super(mailSender, templateEngine);
    }

    public void sent(PaymentEvent event){

        String subject = "Result of Funds Transfer";
        String personal = "QU PLUS";
        String template = "payment-template.html";

        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(event.getDate());

        try {
            //fill Payment information
            Context ctx = new Context();
            ctx.setVariable("sourcePhoneNumber",event.getHashSourcePhoneNumber());
            ctx.setVariable("paymentStatus",event.getStatus());
            ctx.setVariable("date",date);
            ctx.setVariable("transactionNumber",event.getTransactionNumber());
            ctx.setVariable("sourceAccountNumber",event.getHashSourceAcountNumber());
            ctx.setVariable("destBank",event.getDestBank());
            ctx.setVariable("destAccountNumber",event.getHashDestNumber());
            ctx.setVariable("destAccountName",event.getDestAccountName());
            ctx.setVariable("amount",event.getAmount());
            ctx.setVariable("fee",event.getFee());
            ctx.setVariable("availableBalance",event.getAvailableBalance());


            //create email content
            MimeMessage mimeMessage = super.createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                    subject,template,ctx);

            //send email
            System.out.println("PaymentEmail sending...");
            mailSender.send(mimeMessage);
            System.out.println("PaymentEmail has sent.");

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
        }
    }
    public void sent(PaymentRcEvent event){

        String subject = "Result of Funds Receive";
        String personal = "QU PLUS";
        String template = "payment-rc-template.html";

        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(event.getDate());

        try {
            //fill Payment information
            Context ctx = new Context();
            ctx.setVariable("destPhoneNumber",event.getHashDestPhoneNumber());
            ctx.setVariable("date",date);
            ctx.setVariable("sourceName",event.getSourceName());
            ctx.setVariable("sourceAccountNumber",event.getHashSourceAcountNumber());
            ctx.setVariable("sourceBankName",event.getSourceBankName());
            ctx.setVariable("destAccountNumber",event.getHashDestNumber());
            ctx.setVariable("destAccountName",event.getDestAccountName());
            ctx.setVariable("amount",event.getAmount());
            ctx.setVariable("fee",event.getFee());
            ctx.setVariable("availableBalance",event.getAvailableBalance());


            //create email content
            MimeMessage mimeMessage = super.createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                    subject,template,ctx);

            //send email
            System.out.println("PaymentEmail sending...");
            mailSender.send(mimeMessage);
            System.out.println("PaymentEmail has sent.");

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
        }
    }
}
