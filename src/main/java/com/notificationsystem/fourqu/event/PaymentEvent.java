package com.notificationsystem.fourqu.event;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
public class PaymentEvent extends EmailEvent{
    @NotBlank
    @NotNull
    private Date date;
    @NotBlank
    @NotNull
    private String transactionNumber;
    @NotBlank
    @NotNull
    private String sourceAccountNumber;
    @NotBlank
    @NotNull
    private String destBank;
    @NotBlank
    @NotNull
    private String destAccountNumber;
    @NotBlank
    @NotNull
    private String destAccountName;
    @NotBlank
    @NotNull
    private String status;
    @NotBlank
    @NotNull
    private String sourcePhoneNumber;
    @NotBlank
    @NotNull
    private float amount;
    @NotBlank
    @NotNull
    private float fee;
    @NotBlank
    @NotNull
    private float availableBalance;

    public PaymentEvent() {
    }

    public PaymentEvent(String destEmail,
                        String status,
                        String sourcePhoneNumber,
                        @DateTimeFormat Date date,
                        String transactionNumber,
                        String sourceAccountNumber,
                        String destBank,
                        String destAccountNumber,
                        String destAccountName,
                        float amount,
                        float fee,
                        float availableBalance){
        super(destEmail);
        setStatus(status);
        setSourcePhoneNumber(sourcePhoneNumber);
        setDate(date);
        setTransactionNumber(transactionNumber);
        setSourceAccountNumber(sourceAccountNumber);
        setDestBank(destBank);
        setDestAccountNumber(destAccountNumber);
        setDestAccountName(destAccountName);
        setAmount(amount);
        setFee(fee);
        setAvailableBalance(availableBalance);
    }

//    public PaymentEvent(@JsonProperty("destEmail") String destEmail,
//                        @JsonProperty("paymentStatus") String status,
//                        @JsonProperty("sourcePhoneNumber") String sourcePhoneNumber,
//                        @DateTimeFormat @JsonProperty("date") Date date,
//                        @JsonProperty("transactionNumber")String transactionNumber,
//                        @JsonProperty("sourceAccountNumber")String sourceAccountNumber,
//                        @JsonProperty("destBank")String destBank,
//                        @JsonProperty("destAccountNumber")String destAccountNumber,
//                        @JsonProperty("destAccountName")String destAccountName,
//                        @JsonProperty("amount") float amount,
//                        @JsonProperty("fee") float fee,
//                        @JsonProperty("availableBalance") float availableBalance) throws ParseException {
//        super(destEmail);
//        setStatus(status);
//        setSourcePhoneNumber(sourcePhoneNumber);
//        setDate(date);
//        setTransactionNumber(transactionNumber);
//        setSourceAccountNumber(sourceAccountNumber);
//        setDestBank(destBank);
//        setDestAccountNumber(destAccountNumber);
//        setDestAccountName(destAccountName);
//        setAmount(amount);
//        setFee(fee);
//        setAvailableBalance(availableBalance);
//    }

    public Date getDate() {
        return this.date;
    }

    public String getTransactionNumber() {
        return this.transactionNumber;
    }

    public String getSourceAccountNumber() {
        return this.sourceAccountNumber;
    }

    public String getDestBank() {
        return this.destBank;
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

    public void setTransactionNumber(String refNumber) {
        this.transactionNumber = refNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public void setDestBank(String destBank) {
        this.destBank = destBank;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourcePhoneNumber() {
        return this.sourcePhoneNumber;
    }

    public void setSourcePhoneNumber(String sourcePhoneNumber) {
        this.sourcePhoneNumber = sourcePhoneNumber;
    }

    public String getHashSourcePhoneNumber() {
        String result = "";
        for (int i = 0 ; i < this.sourcePhoneNumber.length() ; i++) {
            if(i>=2&&i<=5) result += "X";
            else result += this.sourcePhoneNumber.charAt(i);

            if(i==1||i==5) result += "-";
        }
        return result;
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
            if (i<6) result += "X";
            else result += this.destAccountNumber.charAt(i);

            if (i==2 || i==3 || i==8) result += "-";
        }
        return result;
    }
}
