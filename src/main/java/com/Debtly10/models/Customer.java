package com.Debtly10.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="contact",nullable = false,unique = true)
    private String contact;

    @Column(name="address",nullable = false)
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Mortgage> mortgageList;

    public Customer(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("contact") String contact,
            @JsonProperty("address") String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

    public Customer(Long id){
        this.id=id;
    }
}
