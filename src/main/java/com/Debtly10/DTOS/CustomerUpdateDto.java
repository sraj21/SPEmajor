package com.Debtly10.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerUpdateDto {
    private String firstname;
    private String lastname;
     private String email;

     private String contact;

     private String address;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerUpdateDto(
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("email") String email,
            @JsonProperty("contact") String contact,
            @JsonProperty("address") String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }
}
