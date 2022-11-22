package com.notificationsystem.fourqu;

import com.notificationsystem.fourqu.emailService.PaymentEmailService;
import com.notificationsystem.fourqu.event.PaymentEvent;
import com.notificationsystem.fourqu.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class PDFGeneratorRunner implements CommandLineRunner {
    @Autowired
    private PaymentEmailService paymentEmailService;

    @Override
    public void run(String... args) throws Exception {
        //create date
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date1 = simpleDateFormat.parse("12-01-2018");
        Date date2 = simpleDateFormat.parse("12-02-2018");

        //create payment
        Payment payment1 = new Payment(date1,"something", 12.12f,12323.21f);
        Payment payment2 = new Payment(date2,"something", 13.13f,12788.34f);

        List<Payment> statement = new ArrayList<>();

        for(int i = 0 ; i < 2500 ; i++) {
            statement.add(payment1);
            statement.add(payment2);
        }

        String name = "phuwadon";
        String receiver = "pmmp1599@gmail.com";
        String status = "Success";
        String sPhoneNo = "0999999999";
        String transactionNumber = "012291190014CPP01655";
        String sourceAccountNumber= "xxx-x-x1288-x";
        String destBank ="KASIKORNBANK";
        String destAccountNumber = "029-2-52366-1";
        String destAccountName = "VANNAPHA SIRIWAT";
        float amount = 14.00F;
        float  fee = 0.00F;
        float availableBalance = 729.87F;


        List<PaymentEvent> events = new ArrayList<PaymentEvent>();

        for (int i = 0 ; i < 100 ; i++) {
//            long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            PaymentEvent paymentEvent = new PaymentEvent(receiver,status,sPhoneNo,date1,transactionNumber,sourceAccountNumber,
                    destBank,destAccountNumber,destAccountName,amount,fee,availableBalance);
            events.add(paymentEvent);
        }

//        for (PaymentEvent event : events) {
//            long start = System.nanoTime();
//            this.paymentEmailService.update(event);
//        }

    }
}
