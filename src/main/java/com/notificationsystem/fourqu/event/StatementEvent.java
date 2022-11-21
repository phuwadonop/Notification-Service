package com.notificationsystem.fourqu.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notificationsystem.fourqu.model.Payment;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class StatementEvent extends EmailEvent{
    @NotBlank
    @NotNull
    private List<Payment> paymentList;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String accountNumber;

    public StatementEvent() {
    }

//    public StatementEvent(
//                          String destEmail,
//                          List<Payment> paymentList,
//                          String name,
//                          String accountNumber) {
//        super(destEmail);
//        this.paymentList = paymentList;
//        this.name = name;
//        this.accountNumber = accountNumber;
//    }

    public StatementEvent(@JsonProperty("destEmail")String destEmail,
                          @JsonProperty("statement")List<Payment> paymentList,
                          @JsonProperty("name") String name,
                          @JsonProperty("accountNumber") String accountNumber) {
        super(destEmail);
        this.paymentList = paymentList;
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHashAccountNumber() {
        String result = "";
        for(int i = 0 ; i < this.accountNumber.length() ; i++){
            if (i<6) result += "X";
            else result += this.accountNumber.charAt(i);

            if (i==2 || i==3 || i==8) result += "-";
        }
        return result;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
