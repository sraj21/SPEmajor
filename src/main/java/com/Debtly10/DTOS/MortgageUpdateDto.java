package com.Debtly10.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class MortgageUpdateDto {

    private String productName;
    private float marketValue;
    private float leftAmount;
    private float givenAmount;
    private Date issueDate;
    private Date lastPaid;

    private float interestRate;

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(float marketValue) {
        this.marketValue = marketValue;
    }

    public float getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(float leftAmount) {
        this.leftAmount = leftAmount;
    }

    public float getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(float givenAmount) {
        this.givenAmount = givenAmount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getLastPaid() {
        return lastPaid;
    }

    public void setLastPaid(Date lastPaid) {
        this.lastPaid = lastPaid;
    }

    public MortgageUpdateDto(

            @JsonProperty("productName") String productName,
            @JsonProperty("marketValue") float marketValue,
            @JsonProperty("leftAmount") float leftAmount,
            @JsonProperty("givenAmount") float givenAmount,
            @JsonProperty("issueDate") Date issueDate,
            @JsonProperty("lastPaid") Date lastPaid,
            @JsonProperty("interestRate") float interestRate) {
        this.productName = productName;
        this.marketValue = marketValue;
        this.leftAmount = leftAmount;
        this.givenAmount = givenAmount;
        this.issueDate = issueDate;
        this.lastPaid = lastPaid;
        this.interestRate=interestRate;
    }
}
