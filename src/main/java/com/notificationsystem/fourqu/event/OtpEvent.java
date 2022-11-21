package com.notificationsystem.fourqu.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.String;

@Component
public class OtpEvent extends EmailEvent {
    @NotBlank
    @NotNull
    private String otp;

    public OtpEvent() {
    }

    public OtpEvent(@JsonProperty("destEmail") String destEmail,
                    @JsonProperty("OTP") String otp)
    {
        super(destEmail);
        setOtp(otp);
    }

    public String getOtp() {
        return this.otp;
    }

    private void setOtp(String otp) { this.otp = otp; }

}
