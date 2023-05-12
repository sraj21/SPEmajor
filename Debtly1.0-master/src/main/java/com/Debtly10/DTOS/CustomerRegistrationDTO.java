package com.Debtly10.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRegistrationDTO {

    private String email;
    private String address;
    private String firstname;
    private String lastname;
    private String contact;

    public CustomerRegistrationDTO(@JsonProperty("email") String email,
                                 @JsonProperty("firstname") String firstname,
                                 @JsonProperty("lastname") String lastname,
                                 @JsonProperty("address") String address,
                                 @JsonProperty("contact") String contact) {
        this.email = email;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getContact() {
        return contact;
    }
}
