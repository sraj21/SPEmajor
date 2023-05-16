package com.Debtly10.controller;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.PaymentFetchDTO;
import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Services.PaymentService;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
@CrossOrigin
@RestController
@RequestMapping("/payment")
@Slf4j

public class paymentController {
    private PaymentService paymentService;

    public paymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    private static final Logger logger = LogManager.getLogger(paymentController.class);
    @PostMapping("/register_payment/{mid}")
    public String addMortgage(@RequestBody PaymentRegistrationDTO paymentRegistrationDTO, @PathVariable Long mid){
        logger.info("[Registering payment for mortgage] MID: "+mid);
        return paymentService.addPayment(paymentRegistrationDTO, mid);
    }

    @GetMapping("/get_payments")
    public List<PaymentFetchDTO> getPayments(){
        logger.info("[Retrieving all payments]");
        return paymentService.getAllPayment();
    }

    @DeleteMapping("/delete_payment/{pid}")
    public String deletePayment(@PathVariable Long pid)
    {
        paymentService.deletePayment(pid);
        logger.info("[Deleting payment] PID: "+pid);
        return " payment deleted : " + pid;

    }

    @PostMapping("/see_total_due/{mid}")
    public float totalDue(@RequestBody PaymentRegistrationDTO paymentRegistrationDTO,@PathVariable Long mid)
    {
        logger.info("[Retrieving total due for mortgage] MID: "+mid);
        return paymentService.seeDue(paymentRegistrationDTO,mid);
    }

}
