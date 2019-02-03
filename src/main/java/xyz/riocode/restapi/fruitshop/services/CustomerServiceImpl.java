package xyz.riocode.restapi.fruitshop.services;

import org.springframework.stereotype.Service;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.CustomerMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;
import xyz.riocode.restapi.fruitshop.repository.CustomerRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElseThrow(RuntimeException::new));
    }
}
