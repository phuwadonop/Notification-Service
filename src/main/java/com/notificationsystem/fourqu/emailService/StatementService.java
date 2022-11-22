package com.notificationsystem.fourqu.emailService;

import com.notificationsystem.fourqu.emailSender.StatementSender;
import com.notificationsystem.fourqu.event.StatementEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StatementService {
    @Autowired
    private StatementSender statementSender;

    @Async
    @EventListener
    public void update(StatementEvent event) throws IOException, InterruptedException {
        System.out.println("Listener update statementEvent");
        statementSender.sent(event);
    }
}
