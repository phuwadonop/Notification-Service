package com.notificationsystem.fourqu;

import com.notificationsystem.fourqu.emailService.StatementService;
import com.notificationsystem.fourqu.event.StatementEvent;
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
    private StatementService statementService;

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

        List<StatementEvent> events = new ArrayList<StatementEvent>();

        for (int i = 0 ; i < 1 ; i++) {
            long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            StatementEvent statementEvent = new StatementEvent(receiver,statement,name,String.valueOf(number));
            events.add(statementEvent);
        }

//        for (StatementEvent event : events) {
//            long start = System.nanoTime();
//            this.statementService.update(event);
//        }

    }
}
