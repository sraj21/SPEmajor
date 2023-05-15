package com.Debtly10.controller;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.CustomerUpdateDto;
import com.Debtly10.Services.CustomerService;
import com.Debtly10.models.Customer;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
@CrossOrigin
@RestController
@RequestMapping("/customer")
@Slf4j
public class customerController {

    private CustomerService customerService;

    public customerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    private static final Logger logger = LogManager.getLogger(customerController.class);
    @PostMapping("/register_customer")
    public String addCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO){
        logger.info("[Adding new customer]");
        return customerService.addCustomer(customerRegistrationDTO);
    }


    @GetMapping("/get_customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        logger.info("[Retrieving customer] CID: "+id);
        return customerService.getCustomer(id);
    }

    @GetMapping("/get_customers")
    public List<Customer> getCustomer(){
        logger.info("[Retrieving all customers]");
        return customerService.getAllCustomer();
    }

    @DeleteMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable Long id)
    {
        logger.info("[Deleting customer] CID: "+id);
       return customerService.deleteCustomer(id);

    }

    @PatchMapping("/update_customer/{id}")
    public String updateCustomer(@RequestBody CustomerUpdateDto customerUpdateDto,
                                                 @PathVariable Long id) {
        logger.info("[Updating customer] CID: "+id);
        return customerService.updateCustomer(customerUpdateDto, id);

    }


}
