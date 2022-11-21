package com.notificationsystem.fourqu.emailService;

import com.notificationsystem.fourqu.event.ActivityEvent;
import com.notificationsystem.fourqu.emailSender.ActivitySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ActivityEmailService {
    @Autowired
    private ActivitySender activitySender;
    @Async
    @EventListener
    public void update(ActivityEvent event) {
        System.out.println("Listener update activityEvent");
        activitySender.sent(event);}
}
