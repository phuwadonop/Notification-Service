package com.notificationsystem.fourqu.event;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
public class RegisteredEvent extends EmailEvent {
    @NotBlank
    @NotNull
    private String name;

    public RegisteredEvent() {
    }

    public RegisteredEvent(String destEmail, String name) {
        super(destEmail);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
