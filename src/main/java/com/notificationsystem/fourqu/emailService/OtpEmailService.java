package com.notificationsystem.fourqu.emailService;

import com.notificationsystem.fourqu.event.OtpEvent;
import com.notificationsystem.fourqu.emailSender.OtpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OtpEmailService {
    @Autowired
    private OtpSender otpSender;

    @Async
    @EventListener
    public void update(OtpEvent event) throws InterruptedException {
        System.out.println("Listener update otpEvent");
        otpSender.sent(event);
    }
}

