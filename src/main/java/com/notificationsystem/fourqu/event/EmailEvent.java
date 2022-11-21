package com.notificationsystem.fourqu.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class EmailEvent {
    private final String sourceEmail = "phuadjan@gmail.com";
    @NotBlank
    @NotNull
    private String destEmail;

    public EmailEvent() {
    }

    public EmailEvent (@JsonProperty("destEmail") String destEmail)
    {
        setDestEmail(destEmail);
    }


    public String getDestEmail() {
        return this.destEmail;
    }

    public String getSourceEmail() {
        return sourceEmail;
    }

    public void setDestEmail(String destEmail) {
        this.destEmail = destEmail;
    }

}
