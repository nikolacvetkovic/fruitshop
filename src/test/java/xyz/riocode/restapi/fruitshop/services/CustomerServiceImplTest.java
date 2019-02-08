package xyz.riocode.restapi.fruitshop.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.CustomerMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;
import xyz.riocode.restapi.fruitshop.domain.Customer;
import xyz.riocode.restapi.fruitshop.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    static final Long ID = 1L;
    static final String FIRST_NAME = "Ana";
    static final String CUSTOMER_URL = "/api/v1/customers/1";

    @Mock
    CustomerRepository customerRepository;

    CustomerServiceImpl customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customersDTO = customerService.getAllCustomers();

        assertEquals(3, customersDTO.size());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertNotNull(customerDTO);
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(CUSTOMER_URL, customerDTO.getCustomerUrl());

    }

    @Test
    public void testCreateNewCustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO savedCustomerDTO = customerService.createNewCustomer(customerDTO);

        assertEquals(FIRST_NAME, savedCustomerDTO.getFirstName());
        assertEquals(CUSTOMER_URL, savedCustomerDTO.getCustomerUrl());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO savedCustomerDTO = customerService.updateCustomer(ID, customerDTO);

        assertEquals(FIRST_NAME, savedCustomerDTO.getFirstName());
        assertEquals(CUSTOMER_URL, savedCustomerDTO.getCustomerUrl());
    }

    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(ID);

        verify(customerRepository).deleteById(anyLong());
    }
}