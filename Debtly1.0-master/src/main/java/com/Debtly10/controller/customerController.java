package com.Debtly10.controller;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.CustomerUpdateDto;
import com.Debtly10.Services.CustomerService;
import com.Debtly10.models.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class customerController {

    private CustomerService customerService;

    public customerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register_customer")
    public String addCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO){
       return customerService.addCustomer(customerRegistrationDTO);
    }


    @GetMapping("/get_customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/get_customers")
    public List<Customer> getCustomer(){
        return customerService.getAllCustomer();
    }

    @DeleteMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable Long id)
    {
       return customerService.deleteCustomer(id);

    }

    @PatchMapping("/update_customer/{id}")
    public String updateCustomer(@RequestBody CustomerUpdateDto customerUpdateDto,
                                                 @PathVariable Long id) {
        return customerService.updateCustomer(customerUpdateDto, id);

    }


}
