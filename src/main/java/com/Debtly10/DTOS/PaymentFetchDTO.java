package com.Debtly10.DTOS;

import java.sql.Date;

public class PaymentFetchDTO {
    private String productName;

    private float givenAmount;

    private float leftAmount;

    private float interestRate;

    private Long pid;
    private Long mid;
    private String customerFirstName;
    private String customerLastName;

    public float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public PaymentFetchDTO(String productName, float givenAmount, float leftAmount, float interestRate, Long pid, Long mid, String customerFirstName, String customerLastName, float amount, Date date) {
        this.productName = productName;
        this.givenAmount = givenAmount;
        this.leftAmount = leftAmount;
        this.interestRate = interestRate;
        this.pid = pid;
        this.mid = mid;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.amount = amount;
        this.date = date;
    }

    private float amount;
    private Date date;

    public String getProductName() {
        return productName;
    }

    public float getGivenAmount() {
        return givenAmount;
    }

    public float getLeftAmount() {
        return leftAmount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public Long getPid() {
        return pid;
    }

    public Long getMid() {
        return mid;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }


}
