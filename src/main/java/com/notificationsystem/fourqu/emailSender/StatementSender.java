package com.notificationsystem.fourqu.emailSender;

import com.notificationsystem.fourqu.event.StatementEvent;
import com.notificationsystem.fourqu.filegenerator.PDFGenerator;
import com.notificationsystem.fourqu.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Component
public class StatementSender extends EmailSender{

    public StatementSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        super(mailSender, templateEngine);
    }


    public void sent(StatementEvent event) throws IOException {

        String subject = "E-Statement By QU PLUS";
        String personal = "QU PLUS";
        String template = "e-stm-template.html";

        String pwd = event.getAccountNumber().substring(0,4);

        //create statement.pdf file
        createStatementFile(event.getPaymentList(),"statement-"+event.getAccountNumber()+".pdf",pwd);

        try {
            Context ctx = new Context();
            ctx.setVariable("name",event.getName());
            ctx.setVariable("accountNumber",event.getHashAccountNumber());
            MimeMessage mimeMessage = createEmailContent(event.getSourceEmail(),personal,event.getDestEmail(),
                    subject,template,event.getAccountNumber(),ctx);

            System.out.println("Statement sending...");
            mailSender.send(mimeMessage);
            System.out.println("Statement has sent.");

            File file = new File("statement-"+event.getAccountNumber()+".pdf");
            FileUtils.forceDelete(file);

        } catch (Exception e){
            System.out.printf("Error: %s\n",e.getMessage());
        }
    }

    private void createStatementFile(List<Payment> paymentList, String pdfFileName , String password) throws IOException {
//        Runnable genStatement = new PDFGenerator(paymentList,pdfFileName,password);
//        Thread worker = new Thread(genStatement);
//        worker.start();
//        System.out.println(worker.getName());
        PDFGenerator genStatement = new PDFGenerator(paymentList,pdfFileName,password);
        genStatement.generateStatement();
    }

    private MimeMessage createEmailContent(String sourceEmail, String personal, String destEmail,
                                             String subject, String contentTemplate ,String accountNumber, Context ctx)
            throws MessagingException, UnsupportedEncodingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        message.setSubject(subject);
        message.setFrom(new InternetAddress(sourceEmail,personal));
        message.setTo(destEmail);
        File file = new File("statement-"+accountNumber+".pdf");
        message.addAttachment("statement.pdf", file);
        String content = templateEngine.process(contentTemplate,ctx);
        message.setText(content,true);
        return mimeMessage;
    }

}
