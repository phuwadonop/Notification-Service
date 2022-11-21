package com.notificationsystem.fourqu.controller;

import com.notificationsystem.fourqu.event.*;
import com.notificationsystem.fourqu.publisher.EmailPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/email-notification")
public class EmailController {

    @Autowired
    private EmailPublisher emailPublisher;

    // send OTP notification API
    @PostMapping("/otp")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendOtpEmail(@RequestBody OtpEvent event) {
        emailPublisher.publishEmailEvent(event);
        return new Response("OTP is sending...");
    }

    // send Payment notification API
    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendPaymentEmail(@RequestBody PaymentEvent event) {
        emailPublisher.publishEmailEvent(event);
        return new Response("Payment notification is sending...");
    }

    @PostMapping("/payment-rc")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendPaymentEmail(@RequestBody PaymentRcEvent event) {
        emailPublisher.publishEmailEvent(event);
        return new Response("PaymentRc notification is sending...");
    }

    @PostMapping("/activity")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendActivityEmail(@RequestBody ActivityEvent event){
        emailPublisher.publishEmailEvent(event);
        return new Response("Activity notification is sending...");
    }

    @PostMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendWelcomeEmail(@RequestBody RegisteredEvent event){
        emailPublisher.publishEmailEvent(event);
        return new Response("Registered notification is sending...");
    }

    @PostMapping("/shop-registered")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendShopRegisterEmail(@RequestBody ShopRegisterEvent event){
        emailPublisher.publishEmailEvent(event);
        return new Response("ShopRegistered notification is sending...");
    }

    @PostMapping("/statement")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response sendStatement(@RequestBody StatementEvent event) {
        emailPublisher.publishEmailEvent(event);
        return new Response("Statement is sending...");
    }
}
