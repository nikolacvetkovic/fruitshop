package xyz.riocode.restapi.fruitshop.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;
import xyz.riocode.restapi.fruitshop.controllers.RestResponseEntityExceptionHandler;
import xyz.riocode.restapi.fruitshop.exceptions.ResourceNotFoundException;
import xyz.riocode.restapi.fruitshop.services.CustomerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Ana";
    public static final String LAST_NAME = "Zdravkovic";
    public static final String CUSTOMER_URL = "/api/v1/customers/1";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    CustomerDTO customer1;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
        customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);
        customer1.setCustomerUrl(CustomerController.CUSTOMER_BASE_URL + ID);
    }

    @Test
    public void testListCustomers() throws Exception {


        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Nikola");
        customer2.setLastName("Cvetkovic");

        CustomerDTO customer3 = new CustomerDTO();
        customer3.setFirstName("Marija");
        customer3.setLastName("Zdravkovic");

        List<CustomerDTO> customersDTO = Arrays.asList(customer1, customer2, customer3);

        when(customerService.getAllCustomers()).thenReturn(customersDTO);

        mockMvc.perform(get(CustomerController.CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));


    }

    @Test
    public void testGetCustomerById() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get(CustomerController.CUSTOMER_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));

    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.CUSTOMER_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(customerDTO.getFirstName());
        customerDTO1.setCustomerUrl(CUSTOMER_URL);

        when(customerService.createNewCustomer(any())).thenReturn(customerDTO1);

        ObjectMapper om = new ObjectMapper();

        mockMvc.perform(post(CustomerController.CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer1.getFirstName());
        customerDTO.setLastName(customer1.getLastName());

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(customer1.getFirstName());
        customerDTO1.setLastName(customer1.getLastName());
        customerDTO1.setCustomerUrl(CUSTOMER_URL);

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO1);

        ObjectMapper om = new ObjectMapper();

        mockMvc.perform(put(CustomerController.CUSTOMER_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));
    }

    @Test
    public void testPatchCustomerFirstName() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer1.getFirstName());

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO);

        ObjectMapper om = new ObjectMapper();

        mockMvc.perform(patch(CustomerController.CUSTOMER_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(null)));
    }

    @Test
    public void testPatchCustomerLastName() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(customer1.getLastName());

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTO);

        ObjectMapper om = new ObjectMapper();

        mockMvc.perform(patch(CustomerController.CUSTOMER_BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(null)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.CUSTOMER_BASE_URL + "1"))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(anyLong());
    }
}