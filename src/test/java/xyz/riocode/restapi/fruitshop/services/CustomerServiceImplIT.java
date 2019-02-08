package xyz.riocode.restapi.fruitshop.services;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.CustomerMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;
import xyz.riocode.restapi.fruitshop.bootstrap.Bootstrap;
import xyz.riocode.restapi.fruitshop.domain.Customer;
import xyz.riocode.restapi.fruitshop.repository.CategoryRepository;
import xyz.riocode.restapi.fruitshop.repository.CustomerRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();

    }

    @Test
    public void testPatchCustomerFirstName() {
        String updatedName = "UpdatedName";

        Customer originalCustomer = customerRepository.findAll().get(0);
        assertNotNull(originalCustomer);
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(originalCustomer.getId(), customerDTO);

        Customer patchedCustomer = customerRepository.findById(originalCustomer.getId()).get();

        assertNotNull(patchedCustomer);
        assertThat(originalLastName, Matchers.equalTo(patchedCustomer.getLastName()));
        assertEquals(updatedName, patchedCustomer.getFirstName());

    }

    @Test
    public void testPatchCustomerLastName() {
        String updatedName = "UpdatedName";

        Customer originalCustomer = customerRepository.findAll().get(0);
        assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(originalCustomer.getId(), customerDTO);

        Customer patchedCustomer = customerRepository.findById(originalCustomer.getId()).get();

        assertNotNull(patchedCustomer);
        assertThat(updatedName, Matchers.equalTo(patchedCustomer.getLastName()));
        assertEquals(originalFirstName, patchedCustomer.getFirstName());

    }
}
