package com.Debtly10.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.sql.Date;

public class MortgageFetchDTO {
    private String productName;

    private float marketValue;

    private float givenAmount;

    private float leftAmount;

    private Date issueDate;

    private Date lastPaid;

    private float interestRate;

    private Long cid;
    private String customerFirstName;
    private String customerLastName;

    public String getProductName() {
        return productName;
    }

    public float getMarketValue() {
        return marketValue;
    }

    public float getGivenAmount() {
        return givenAmount;
    }

    public float getLeftAmount() {
        return leftAmount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getLastPaid() {
        return lastPaid;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public Long getCid() {
        return cid;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public MortgageFetchDTO(String productName,
                            float marketValue,
                            float givenAmount,
                            float leftAmount,
                            Date issueDate,
                            Date lastPaid,
                            float interestRate,
                            Long cid,
                            String customerFirstName,
                            String customerLastName) {
        this.productName = productName;
        this.marketValue = marketValue;
        this.givenAmount = givenAmount;
        this.leftAmount = leftAmount;
        this.issueDate = issueDate;
        this.lastPaid = lastPaid;
        this.interestRate = interestRate;
        this.cid = cid;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }
}
