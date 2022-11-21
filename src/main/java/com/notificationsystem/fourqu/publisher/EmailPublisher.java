package com.notificationsystem.fourqu.publisher;

import com.notificationsystem.fourqu.event.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EmailPublisher {
    private final ApplicationEventPublisher eventPublisher;

    EmailPublisher(ApplicationEventPublisher publisher){
        this.eventPublisher = publisher;
    }

    // public void publishOtpEvent(OtpEvent event) {
    //     System.out.println("publish otpEvent");
    //     eventPublisher.publishEvent(event);
    // }

    // public void publishPaymentEvent(PaymentEvent event) {
    //     System.out.println("publish paymentEvent");
    //     eventPublisher.publishEvent(event);
    // }

    // public void publishPaymentEvent(PaymentRcEvent event) {
    //     System.out.println("publish paymentRcEvent");
    //     eventPublisher.publishEvent(event);
    // }

    // public void publishActivityEvent(ActivityEvent event){
    //     System.out.println("publish activityEvent");
    //     eventPublisher.publishEvent(event);
    // }

    // public void publishRegisteredEvent(RegisteredEvent event){
    //     System.out.println("publish registeredEvent");
    //     eventPublisher.publishEvent(event);
    // }

    // public void publishShopRegisterEvent(ShopRegisterEvent event){
    //     System.out.println("publish registeredEvent");
    //     eventPublisher.publishEvent(event);
    // }


    // public void publishStatementEvent(StatementEvent event){
    //     System.out.println("publish statementEvent");
    //     eventPublisher.publishEvent(event);
    // }

    public void publishEmailEvent(Object event){
        System.out.println("Publish EmailEvent");
        eventPublisher.publishEvent(event);
    }

}
