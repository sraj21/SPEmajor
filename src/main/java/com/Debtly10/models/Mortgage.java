package com.Debtly10.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "mortgage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mortgage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;


    private float marketValue;

    @Column(nullable = false)
    private float givenAmount;

    @Column(nullable = false)
    private float leftAmount;

    @Column(nullable = false)
    private Date issueDate;

    private Date lastPaid;

    private float interestRate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "mortgage", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Payment> paymentList;


}
