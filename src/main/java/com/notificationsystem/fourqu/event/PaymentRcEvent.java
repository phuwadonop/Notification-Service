package com.notificationsystem.fourqu.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

@Component
public class PaymentRcEvent extends EmailEvent{
    @NotBlank
    @NotNull
    private Date date;
    @NotBlank
    @NotNull
    private String sourceBankName;
    @NotBlank
    @NotNull
    private String sourceName;
    @NotBlank
    @NotNull
    private String sourceAccountNumber;
    @NotBlank
    @NotNull
    private String destAccountNumber;
    @NotBlank
    @NotNull
    private String destAccountName;
    @NotBlank
    @NotNull
    private float amount;
    @NotBlank
    @NotNull
    private float fee;
    @NotBlank
    @NotNull
    private float availableBalance;

    @NotBlank
    @NotNull
    private String destPhoneNumber;


    public PaymentRcEvent() {
    }

    public PaymentRcEvent(  @JsonProperty("destEmail") String destEmail,
                            @JsonProperty("sourceBankName") String sourceBankName,
                            @JsonProperty("sourceName") String sourceName,
                            @DateTimeFormat @JsonProperty("date") Date date,
                            @JsonProperty("sourceAccountNumber")String sourceAccountNumber,
                            @JsonProperty("destAccountNumber")String destAccountNumber,
                            @JsonProperty("destAccountName")String destAccountName,
                            @JsonProperty("amount") float amount,
                            @JsonProperty("destPhoneNumber") String destPhoneNumber,
                            @JsonProperty("fee") float fee,
                            @JsonProperty("availableBalance") float availableBalance) throws ParseException {
        super(destEmail);
        setSourceBankName(sourceBankName);
        setDate(date);
        setSourceAccountNumber(sourceAccountNumber);
        setDestAccountNumber(destAccountNumber);
        setDestAccountName(destAccountName);
        setAmount(amount);
        setFee(fee);
        setAvailableBalance(availableBalance);
        setSourceName(sourceName);
        setDestPhoneNumber(destPhoneNumber);
    }
    public String getDestPhoneNumber() {
        return destPhoneNumber;
    }

    public String getHashDestPhoneNumber() {
        String result = "";
        for (int i = 0 ; i < this.destPhoneNumber.length() ; i++) {
            if(i>=6&&i<=9) result += this.destPhoneNumber.charAt(i);
            else result += "X";

            if(i==2||i==5) result += "-";
        }
        return result;
    }

    public void setDestPhoneNumber(String destPhoneNumber) {
        this.destPhoneNumber = destPhoneNumber;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceBankName() {
        return sourceBankName;
    }

    public void setSourceBankName(String sourceBankName) {
        this.sourceBankName = sourceBankName;
    }

    public Date getDate() {
        return this.date;
    }


    public String getSourceAccountNumber() {
        return this.sourceAccountNumber;
    }


    public String getDestAccountNumber() {
        return this.destAccountNumber;
    }

    public String getDestAccountName() {
        return this.destAccountName;
    }

    public float getAmount() {
        return this.amount;
    }

    public float getFee() {
        return this.fee;
    }

    public float getAvailableBalance() {
        return this.availableBalance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }


    public void setDestAccountNumber(String destAccountNumber) {
        this.destAccountNumber = destAccountNumber;
    }

    public void setDestAccountName(String destAccountName) {
        this.destAccountName = destAccountName;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }

    public  String getHashSourceAcountNumber() {
        String result = "";
        for (int i = 0 ; i < this.sourceAccountNumber.length() ; i++) {
            if(i>=5&&i<=8) result += this.sourceAccountNumber.charAt(i);
            else result += "X";

            if(i==2||i==3||i==8) result += "-";
        }
        return result;
    }

    public String getHashDestNumber() {
        String result = "";
        for(int i = 0 ; i < this.destAccountNumber.length() ; i++){
            if(i>=5&&i<=8) result += this.destAccountNumber.charAt(i);
            else result += "X";

            if (i==2 || i==3 || i==8) result += "-";
        }
        return result;
    }
}
