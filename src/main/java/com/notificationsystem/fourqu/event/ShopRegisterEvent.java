package com.notificationsystem.fourqu.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
public class ShopRegisterEvent extends EmailEvent {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String shopName;

    public ShopRegisterEvent() {
    }

    public ShopRegisterEvent(@JsonProperty("destEmail") String destEmail,
                             @JsonProperty("name") String name,
                             @JsonProperty("shopName") String shopName) {
        super(destEmail);
        this.name = name;
        this.shopName = shopName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
