package com.Debtly10.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.CustomerUpdateDto;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.Services.CustomerService;
import com.Debtly10.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {customerController.class, CustomerService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class customerControllerTest {
    @Autowired
    private customerController customerController;

    @MockBean
    private CustomerRepository customerRepository;

    /**
     * Method under test: {@link customerController#addCustomer(CustomerRegistrationDTO)}
     */
    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/customer/register_customer")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new CustomerRegistrationDTO("jane.doe@example.org", "Jane", "Doe", "42 Main St", "Contact")));
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer registered Successfully"));
    }

    /**
     * Method under test: {@link customerController#deleteCustomer(Long)}
     */
    @Test
    public void testDeleteCustomer() throws Exception {
        doNothing().when(customerRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/delete_customer/{id}",
                1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("customer deleted successfully"));
    }

    /**
     * Method under test: {@link customerController#deleteCustomer(Long)}
     */
    @Test
    public void testDeleteCustomer2() throws Exception {
        doNothing().when(customerRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/delete_customer/{id}",
                1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("customer deleted successfully"));
    }

    /**
     * Method under test: {@link customerController#getCustomer()}
     */
    @Test
    public void testGetCustomer() throws Exception {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link customerController#getCustomer()}
     */
    @Test
    public void testGetCustomer2() throws Exception {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link customerController#getCustomer(Long)}
     */
    @Test
    public void testGetCustomer3() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers/{id}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"contact\":\"Contact\",\"address\":\"42"
                                        + " Main St\",\"id\":1,\"mortgageList\":[]}"));
    }

    /**
     * Method under test: {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    public void testUpdateCustomer() throws Exception {
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

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "");
        customerUpdateDto.setAddress((String) "");
        customerUpdateDto.setContact((String) "");
        customerUpdateDto.setEmail((String) "");
        customerUpdateDto.setLastname((String) "");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer updated Successfully"));
    }

    /**
     * Method under test: {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    public void testUpdateCustomer2() throws Exception {
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

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "?");
        customerUpdateDto.setAddress((String) "");
        customerUpdateDto.setContact((String) "");
        customerUpdateDto.setEmail((String) "");
        customerUpdateDto.setLastname((String) "");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer updated Successfully"));
    }

    /**
     * Method under test: {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    public void testUpdateCustomer3() throws Exception {
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

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "");
        customerUpdateDto.setAddress((String) "?");
        customerUpdateDto.setContact((String) "");
        customerUpdateDto.setEmail((String) "");
        customerUpdateDto.setLastname((String) "");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer updated Successfully"));
    }

    /**
     * Method under test: {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    public void testUpdateCustomer4() throws Exception {
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

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "");
        customerUpdateDto.setAddress((String) "");
        customerUpdateDto.setContact((String) "?");
        customerUpdateDto.setEmail((String) "");
        customerUpdateDto.setLastname((String) "");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer updated Successfully"));
    }

    /**
     * Method under test: {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    public void testUpdateCustomer5() throws Exception {
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

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "");
        customerUpdateDto.setAddress((String) "");
        customerUpdateDto.setContact((String) "");
        customerUpdateDto.setEmail((String) "?");
        customerUpdateDto.setLastname((String) "");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer updated Successfully"));
    }

    /**
     * Method under test: {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    public void testUpdateCustomer6() throws Exception {
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

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "");
        customerUpdateDto.setAddress((String) "");
        customerUpdateDto.setContact((String) "");
        customerUpdateDto.setEmail((String) "");
        customerUpdateDto.setLastname((String) "?");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Customer updated Successfully"));
    }
}

