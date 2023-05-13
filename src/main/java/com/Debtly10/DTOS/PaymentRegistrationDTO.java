package com.Debtly10.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class PaymentRegistrationDTO {

    private float amount;

    private Date date;

    public PaymentRegistrationDTO(
            @JsonProperty("amount") float amount,
            @JsonProperty("date") Date date) {
        this.amount = amount;
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
