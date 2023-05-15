package com.Debtly10.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.Repository.PaymentRepository;
import com.Debtly10.Services.PaymentService;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import com.Debtly10.models.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
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

@ContextConfiguration(classes = {paymentController.class, PaymentService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class paymentControllerTest {
    @MockBean
    private MortgageRepository mortgageRepository;

    @Autowired
    private paymentController paymentController;

    @MockBean
    private PaymentRepository paymentRepository;

    /**
     * Method under test: {@link paymentController#addMortgage(PaymentRegistrationDTO, Long)}
     */
    @Test
    public void testAddMortgage() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(mock(Date.class));
        mortgage.setLastPaid(mock(Date.class));
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("Product Name");

        Payment payment = new Payment();
        payment.setAmount(10.0f);
        payment.setDate(mock(Date.class));
        payment.setId(1L);
        payment.setMortgage(mortgage);
        when(paymentRepository.save(Mockito.<Payment>any())).thenReturn(payment);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);

        Mortgage mortgage2 = new Mortgage();
        mortgage2.setCustomer(customer2);
        mortgage2.setGivenAmount(10.0f);
        mortgage2.setId(1L);
        mortgage2.setInterestRate(10.0f);
        mortgage2.setIssueDate(mock(Date.class));
        mortgage2.setLastPaid(lastPaid);
        mortgage2.setLeftAmount(10.0f);
        mortgage2.setMarketValue(10.0f);
        mortgage2.setPaymentList(new ArrayList<>());
        mortgage2.setProductName("Product Name");
        Optional<Mortgage> ofResult = Optional.of(mortgage2);

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setContact("Contact");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFirstName("Jane");
        customer3.setId(1L);
        customer3.setLastName("Doe");
        customer3.setMortgageList(new ArrayList<>());

        Mortgage mortgage3 = new Mortgage();
        mortgage3.setCustomer(customer3);
        mortgage3.setGivenAmount(10.0f);
        mortgage3.setId(1L);
        mortgage3.setInterestRate(10.0f);
        mortgage3.setIssueDate(mock(Date.class));
        mortgage3.setLastPaid(mock(Date.class));
        mortgage3.setLeftAmount(10.0f);
        mortgage3.setMarketValue(10.0f);
        mortgage3.setPaymentList(new ArrayList<>());
        mortgage3.setProductName("Product Name");
        when(mortgageRepository.save(Mockito.<Mortgage>any())).thenReturn(mortgage3);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        PaymentRegistrationDTO paymentRegistrationDTO = new PaymentRegistrationDTO(10.0f, date);

        String content = (new ObjectMapper()).writeValueAsString(paymentRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/payment/register_payment/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(" payment added successfully0"));
    }

    /**
     * Method under test: {@link paymentController#deletePayment(Long)}
     */
    @Test
    public void testDeletePayment() throws Exception {
        doNothing().when(paymentRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/payment/delete_payment/{pid}", 1L);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(" payment deleted : 1"));
    }

    /**
     * Method under test: {@link paymentController#deletePayment(Long)}
     */
    @Test
    public void testDeletePayment2() throws Exception {
        doNothing().when(paymentRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/payment/delete_payment/{pid}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(" payment deleted : 1"));
    }

    /**
     * Method under test: {@link paymentController#getPayments()}
     */
    @Test
    public void testGetPayments() throws Exception {
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/get_payments");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link paymentController#getPayments()}
     */
    @Test
    public void testGetPayments2() throws Exception {
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/get_payments");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link paymentController#totalDue(PaymentRegistrationDTO, Long)}
     */
    @Test
    public void testTotalDue() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(mock(Date.class));
        mortgage.setLastPaid(lastPaid);
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("Product Name");
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        PaymentRegistrationDTO paymentRegistrationDTO = new PaymentRegistrationDTO(10.0f, date);

        String content = (new ObjectMapper()).writeValueAsString(paymentRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/payment/see_total_due/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }
}

