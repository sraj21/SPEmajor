package com.Debtly10.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.MortgageUpdateDto;
import com.Debtly10.Repository.CustomerRepository;
import com.Debtly10.Repository.MortgageRepository;
import com.Debtly10.Services.MortgageService;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {mortgageController.class, MortgageService.class})
@ExtendWith(SpringExtension.class)
class mortgageControllerDiffblueTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private mortgageController mortgageController;

    @MockBean
    private MortgageRepository mortgageRepository;

    /**
     * Method under test:
     * {@link mortgageController#addMortgage(MortgageRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage() throws Exception {
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
        when(mortgageRepository.save(Mockito.<Mortgage>any())).thenReturn(mortgage);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setContact("Contact");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setMortgageList(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date issueDate = mock(Date.class);
        when(issueDate.getTime()).thenReturn(10L);
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);
        MortgageRegistrationDTO mortgageRegistrationDTO = new MortgageRegistrationDTO("Product Name", 10.0f, 10.0f, 10.0f,
                issueDate, lastPaid, 10.0f);

        String content = (new ObjectMapper()).writeValueAsString(mortgageRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mortgage/register_mortgage/{cid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("mortgage added successfully"));
    }

    /**
     * Method under test: {@link mortgageController#deleteMortgage(Long)}
     */
    @Test
    void testDeleteMortgage() throws Exception {
        doNothing().when(mortgageRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mortgage/delete_mortgage/{mid}", 1L);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("mortgage deleted successfully"));
    }

    /**
     * Method under test: {@link mortgageController#deleteMortgage(Long)}
     */
    @Test
    void testDeleteMortgage2() throws Exception {
        doNothing().when(mortgageRepository).deleteById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mortgage/delete_mortgage/{mid}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("mortgage deleted successfully"));
    }

    /**
     * Method under test: {@link mortgageController#getMortgageByCustomer(Long)}
     */
    @Test
    void testGetMortgageByCustomer() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/mortgage_by_cid/{cid}", 1L);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link mortgageController#getMortgages()}
     */
    @Test
    void testGetMortgages() throws Exception {
        when(mortgageRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/get_mortgages");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link mortgageController#getMortgages()}
     */
    @Test
    void testGetMortgages2() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("?");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        Date issueDate = mock(Date.class);
        when(issueDate.getTime()).thenReturn(10L);
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);

        Mortgage mortgage = new Mortgage();
        mortgage.setCustomer(customer);
        mortgage.setGivenAmount(10.0f);
        mortgage.setId(1L);
        mortgage.setInterestRate(10.0f);
        mortgage.setIssueDate(issueDate);
        mortgage.setLastPaid(lastPaid);
        mortgage.setLeftAmount(10.0f);
        mortgage.setMarketValue(10.0f);
        mortgage.setPaymentList(new ArrayList<>());
        mortgage.setProductName("?");

        ArrayList<Mortgage> mortgageList = new ArrayList<>();
        mortgageList.add(mortgage);
        when(mortgageRepository.findAll()).thenReturn(mortgageList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/get_mortgages");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"productName\":\"?\",\"marketValue\":10.0,\"givenAmount\":10.0,\"leftAmount\":10.0,\"issueDate\":10,\"lastPaid"
                                        + "\":10,\"interestRate\":10.0,\"cid\":1,\"mid\":1,\"customerFirstName\":\"Jane\",\"customerLastName\":\"Doe\"}]"));
    }

    /**
     * Method under test:
     * {@link mortgageController#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage() throws Exception {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MortgageUpdateDto mortgageUpdateDto = new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class),
                mock(Date.class), 10.0f);
        mortgageUpdateDto.setInterestRate(0.0f);
        mortgageUpdateDto.setLastPaid(null);
        mortgageUpdateDto.setGivenAmount(0.0f);
        mortgageUpdateDto.setMarketValue(0.0f);
        mortgageUpdateDto.setLeftAmount(0.0f);
        mortgageUpdateDto.setIssueDate(null);
        String content = (new ObjectMapper()).writeValueAsString(mortgageUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/mortgage/update_mortgage/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("mortgage updated successfully"));
    }

    /**
     * Method under test:
     * {@link mortgageController#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage2() throws Exception {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MortgageUpdateDto mortgageUpdateDto = new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class),
                mock(Date.class), 10.0f);
        mortgageUpdateDto.setInterestRate(10.0f);
        mortgageUpdateDto.setLastPaid(null);
        mortgageUpdateDto.setGivenAmount(0.0f);
        mortgageUpdateDto.setMarketValue(0.0f);
        mortgageUpdateDto.setLeftAmount(0.0f);
        mortgageUpdateDto.setIssueDate(null);
        String content = (new ObjectMapper()).writeValueAsString(mortgageUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/mortgage/update_mortgage/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("mortgage updated successfully"));
    }

    /**
     * Method under test:
     * {@link mortgageController#updateMortgage(MortgageUpdateDto, Long)}
     */
    @Test
    void testUpdateMortgage3() throws Exception {
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
        Optional<Mortgage> ofResult = Optional.of(mortgage);
        when(mortgageRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);

        MortgageUpdateDto mortgageUpdateDto = new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class),
                mock(Date.class), 10.0f);
        mortgageUpdateDto.setInterestRate(0.0f);
        mortgageUpdateDto.setLastPaid(lastPaid);
        mortgageUpdateDto.setGivenAmount(0.0f);
        mortgageUpdateDto.setMarketValue(0.0f);
        mortgageUpdateDto.setLeftAmount(0.0f);
        mortgageUpdateDto.setIssueDate(null);
        String content = (new ObjectMapper()).writeValueAsString(mortgageUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/mortgage/update_mortgage/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("mortgage updated successfully"));
    }
}
