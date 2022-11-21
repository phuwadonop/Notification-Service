package com.notificationsystem.fourqu.emailService;

import com.notificationsystem.fourqu.event.PaymentEvent;
import com.notificationsystem.fourqu.emailSender.PaymentSender;
import com.notificationsystem.fourqu.event.PaymentRcEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PaymentEmailService {
    @Autowired
    private PaymentSender paymentSender;
    @Async
    @EventListener
    public void update(PaymentEvent event){
        System.out.println("Listener update paymentEvent");
        paymentSender.sent(event);
    }
    @Async
    @EventListener
    public void update(PaymentRcEvent event){
        System.out.println("Listener update paymentRcEvent");
        paymentSender.sent(event);
    }
}
