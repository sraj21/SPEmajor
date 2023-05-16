package com.Debtly10.Services;

import com.Debtly10.DTOS.MortgageFetchDTO;
import com.Debtly10.DTOS.PaymentFetchDTO;
import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.Repository.PaymentRepository;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private MortgageRepository mortgageRepository;

    public PaymentService(PaymentRepository paymentRepository, MortgageRepository mortgageRepository) {
        this.paymentRepository = paymentRepository;
        this.mortgageRepository=mortgageRepository;
    }


    public String addPayment(PaymentRegistrationDTO paymentRegistrationDTO, Long mid) {

        Payment payment = new Payment();
        payment.setAmount(paymentRegistrationDTO.getAmount());
        payment.setDate(paymentRegistrationDTO.getDate());
        Mortgage mortgage = mortgageRepository.findById(mid).get();

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        long diff = paymentRegistrationDTO.getDate().getTime()-mortgage.getLastPaid().getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        float rate = mortgage.getInterestRate();
        float interest = (mortgage.getLeftAmount() * diffDays *rate)/100;
        float temp= mortgage.getLeftAmount();
        float newAmount = temp + interest - payment.getAmount();
        mortgage.setLeftAmount(newAmount);
        mortgage.setLastPaid(payment.getDate());
        mortgageRepository.save(mortgage);
        payment.setMortgage(mortgage);
        paymentRepository.save(payment);
        return " payment added successfully" + diffDays;
    }

    public List<PaymentFetchDTO> getAllPayment() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentFetchDTO> dto = new ArrayList<>();
        for (Payment payment : payments) {
            dto.add(new PaymentFetchDTO(
                    payment.getMortgage().getProductName(),
                    payment.getMortgage().getGivenAmount(),
                    payment.getMortgage().getLeftAmount(),
                    payment.getMortgage().getInterestRate(),
                    payment.getMortgage().getId(),
                    payment.getMortgage().getCustomer().getId(),
                    payment.getMortgage().getCustomer().getFirstName(),
                    payment.getMortgage().getCustomer().getLastName(),
                    payment.getAmount(),
                    payment.getDate()
            ));
        }
        return dto;
    }
    public List<MortgageFetchDTO> getAllMortgage() {
        List<Mortgage> mortgages = mortgageRepository.findAll();
        List<MortgageFetchDTO> dto = new ArrayList<>();
        for (Mortgage mortgage : mortgages) {
            dto.add(new MortgageFetchDTO(
                    mortgage.getProductName(),
                    mortgage.getMarketValue(),
                    mortgage.getGivenAmount(),
                    mortgage.getLeftAmount(),
                    mortgage.getIssueDate(),
                    mortgage.getLastPaid(),
                    mortgage.getInterestRate(),
                    mortgage.getId(),
                    mortgage.getCustomer().getId(),
                    mortgage.getCustomer().getFirstName(),
                    mortgage.getCustomer().getLastName()
            ));
        }
        return dto;
    }

    public void deletePayment(Long pid) {
        paymentRepository.deleteById(pid);
    }

    public float seeDue(PaymentRegistrationDTO paymentRegistrationDTO ,Long mid) {
        Mortgage mortgage = mortgageRepository.findById(mid).get();
        Payment payment = new Payment();
        payment.setAmount(paymentRegistrationDTO.getAmount());
        payment.setDate(paymentRegistrationDTO.getDate());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        long diff = paymentRegistrationDTO.getDate().getTime()-mortgage.getLastPaid().getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        float rate = mortgage.getInterestRate();
        float interest = (mortgage.getLeftAmount() * diffDays *rate)/100;
        float temp= mortgage.getLeftAmount();
        float newAmount = temp + interest;
        return newAmount ;
    }


//    Mortgage mortgage= new Mortgage();
//        mortgage.setGivenAmount(mortgageRegistrationDTO.getGivenAmount());
//        mortgage.setLastPaid(mortgageRegistrationDTO.getLastPaid());
//        mortgage.setIssueDate(mortgageRegistrationDTO.getIssueDate());
//        mortgage.setLeftAmount(mortgageRegistrationDTO.getLeftAmount());
//        mortgage.setProductName(mortgageRegistrationDTO.getProductName());
//        mortgage.setMarketValue(mortgageRegistrationDTO.getMarketValue());
//    Customer customer = customerRepository.findById(id).get();
//        mortgage.setCustomer(customer);
//        mortgageRepository.save(mortgage);
//        return "mortgage added successfully";
}
