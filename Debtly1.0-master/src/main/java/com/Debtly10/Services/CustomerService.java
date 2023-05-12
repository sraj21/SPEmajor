package com.Debtly10.Services;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.CustomerUpdateDto;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String addCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerRegistrationDTO.getFirstname());
        customer.setLastName(customerRegistrationDTO.getLastname());
        customer.setEmail(customerRegistrationDTO.getEmail());
        customer.setAddress(customerRegistrationDTO.getAddress());
        customer.setContact(customerRegistrationDTO.getContact());
        customerRepository.save(customer);
        return "Customer registered Successfully";

    }

    public Customer getCustomer(Long Id){
        Customer customer = new Customer();
        customer=customerRepository.findById(Id).get();
        return customer;

    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "customer deleted successfully";
    }

    public String updateCustomer(CustomerUpdateDto customerUpdateDto, Long id) {
        Customer customer = customerRepository.findById(id).get();
        if(customerUpdateDto.getFirstname() != ""){
            customer.setFirstName(customerUpdateDto.getFirstname());
        }
        if(customerUpdateDto.getLastname() != ""){
            customer.setLastName(customerUpdateDto.getLastname());
        }
        if(customerUpdateDto.getAddress() != ""){
            customer.setAddress(customerUpdateDto.getAddress());
        }
        if(customerUpdateDto.getEmail() != ""){
            customer.setEmail(customerUpdateDto.getEmail());
        }
        if(customerUpdateDto.getContact() != ""){
            customer.setContact(customerUpdateDto.getContact());
        }
      customerRepository.save(customer);
        return "Customer updated Successfully";
    }
}
