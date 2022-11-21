package com.notificationsystem.fourqu.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Component
public class ActivityEvent extends EmailEvent{
    @NotBlank
    @NotNull
    private UUID transactionId;
    @NotBlank
    @NotNull
    private UUID accountId;
    @NotBlank
    @NotNull
    private String ipAddress;
    @NotBlank
    @NotNull
    @DateTimeFormat
    private Date timeStamp;

    public ActivityEvent() {
    }

    public ActivityEvent(@JsonProperty("destEmail")String destEmail,
                         @JsonProperty("transactionID")UUID transactionId,
                         @JsonProperty("accountID")UUID accountId,
                         @JsonProperty("IPAddress")String ipAddress,
                         @JsonProperty("timeStamp")Date timeStamp)
    {
        super(destEmail);
        setAccountId(accountId);
        setTransactionId(transactionId);
        setIpAddress(ipAddress);
        setTimeStamp(timeStamp);
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
