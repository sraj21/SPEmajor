package com.Debtly10.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.CustomerUpdateDto;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.models.Customer;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomerServiceDiffblueTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test:
     * {@link CustomerService#addCustomer(CustomerRegistrationDTO)}
     */
    @Test
    void testAddCustomer() {
        customerService
                .addCustomer(new CustomerRegistrationDTO("jane.doe@example.org", "Jane", "Doe", "42 Main St", "Contact"));
        assertEquals(7, customerService.getAllCustomer().size());
    }

    /**
     * Method under test:
     * {@link CustomerService#addCustomer(CustomerRegistrationDTO)}
     */
    @Test
    void testAddCustomer2() {
        customerService.addCustomer(new CustomerRegistrationDTO("jane.doe@example.org", "Jane", "Doe", "42 Main St",
                "Customer registered Successfully"));
        assertEquals(7, customerService.getAllCustomer().size());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(Long)}
     */
    @Test
    void testGetCustomer() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Customer actualCustomer = customerService.getCustomer(1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        assertSame(customer, actualCustomer);
    }

    /**
     * Method under test: {@link CustomerService#getAllCustomer()}
     */
    @Test
    void testGetAllCustomer() {
        assertEquals(3, customerService.getAllCustomer().size());
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer() {
        customerService.deleteCustomer(7L);
        assertEquals(3, customerService.getAllCustomer().size());
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer2() {
        customerService.deleteCustomer(8L);
        assertEquals(3, customerService.getAllCustomer().size());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateCustomerResult = customerService
                .updateCustomer(new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact", "42 Main St"), 1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        assertEquals("Customer updated Successfully", actualUpdateCustomerResult);
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer2() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateCustomerResult = customerService
                .updateCustomer(new CustomerUpdateDto("", "Doe", "jane.doe@example.org", "Contact", "42 Main St"), 1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        assertEquals("Customer updated Successfully", actualUpdateCustomerResult);
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer3() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateCustomerResult = customerService
                .updateCustomer(new CustomerUpdateDto("Jane", "", "jane.doe@example.org", "Contact", "42 Main St"), 1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        assertEquals("Customer updated Successfully", actualUpdateCustomerResult);
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer4() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateCustomerResult = customerService
                .updateCustomer(new CustomerUpdateDto("Jane", "Doe", "", "Contact", "42 Main St"), 1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        assertEquals("Customer updated Successfully", actualUpdateCustomerResult);
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer5() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateCustomerResult = customerService
                .updateCustomer(new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "", "42 Main St"), 1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        assertEquals("Customer updated Successfully", actualUpdateCustomerResult);
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer6() {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        String actualUpdateCustomerResult = customerService
                .updateCustomer(new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact", ""), 1L);
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        assertEquals("Customer updated Successfully", actualUpdateCustomerResult);
    }
}
