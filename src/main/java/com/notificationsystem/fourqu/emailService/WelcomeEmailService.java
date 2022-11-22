package com.notificationsystem.fourqu.emailService;

import com.notificationsystem.fourqu.event.RegisteredEvent;
import com.notificationsystem.fourqu.emailSender.WelcomeEmailSender;
import com.notificationsystem.fourqu.event.ShopRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WelcomeEmailService {
    @Autowired
    private WelcomeEmailSender welcomeSender;
    @Async
    @EventListener
    public void update(RegisteredEvent event) throws InterruptedException {
        System.out.println("Listener update welcomeEvent");
        welcomeSender.sent(event);
    }

    @Async
    @EventListener
    public void update(ShopRegisterEvent event) throws InterruptedException {
        System.out.println("Listener update welcomeEvent");
        welcomeSender.sent(event);
    }
}
