package com.Debtly10.controller;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Services.PaymentService;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class paymentController {
    private PaymentService paymentService;

    public paymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/register_payment/{mid}")
    public String addMortgage(@RequestBody PaymentRegistrationDTO paymentRegistrationDTO, @PathVariable Long mid){
        return paymentService.addPayment(paymentRegistrationDTO, mid);
    }

    @GetMapping("/get_payments")
    public List<Payment> getPayments(){
        return paymentService.getAllPayment();
    }

    @DeleteMapping("/delete_payment/{pid}")
    public String deletePayment(@PathVariable Long pid)
    {
        paymentService.deletePayment(pid);
        return " payment deleted : " + pid;

    }

    @PostMapping("/see_total_due/{mid}")
    public float totalDue(@RequestBody PaymentRegistrationDTO paymentRegistrationDTO,@PathVariable Long mid)
    {
        return paymentService.seeDue(paymentRegistrationDTO,mid);
    }

}
