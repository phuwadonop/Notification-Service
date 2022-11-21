package com.notificationsystem.fourqu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Payment {
    @DateTimeFormat
    private Date dateTime;
    private String description;
    private float paymentAmount;
    private float outstandingBalance;

    public Payment(@JsonProperty("datetime") Date dateTime,
                   @JsonProperty("description") String description,
                   @JsonProperty("paymentAmount") float paymentAmount,
                   @JsonProperty("balance") float outstandingBalance) {
        this.dateTime = dateTime;
        this.description = description;
        this.paymentAmount = paymentAmount;
        this.outstandingBalance = outstandingBalance;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public float getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(float outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
}
