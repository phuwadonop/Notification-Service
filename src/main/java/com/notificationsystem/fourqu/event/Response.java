package com.notificationsystem.fourqu.event;

import lombok.Data;

@Data
public class Response {
    String message;
    public Response (String message) {
        this.message = message;
    }
}
