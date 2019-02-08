package xyz.riocode.restapi.fruitshop.services;

import org.springframework.stereotype.Service;
import xyz.riocode.restapi.fruitshop.api.v1.mapper.CustomerMapper;
import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;
import xyz.riocode.restapi.fruitshop.controllers.v1.CustomerController;
import xyz.riocode.restapi.fruitshop.domain.Customer;
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
                    customerDTO.setCustomerUrl(CustomerController.CUSTOMER_BASE_URL + customer.getId());
                    return customerDTO;
                })
                .collect(toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(CustomerController.CUSTOMER_BASE_URL + customer.getId());

        return customerDTO;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        return saveCustomerAndReturn(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveCustomerAndReturn(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        if(customerDTO.getFirstName() != null)
            customer.setFirstName(customerDTO.getFirstName());
        if(customerDTO.getLastName() != null)
            customer.setLastName(customerDTO.getLastName());
        CustomerDTO savedCustomer = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        savedCustomer.setCustomerUrl(CustomerController.CUSTOMER_BASE_URL + customer.getId());

        return savedCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveCustomerAndReturn(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTOReturn = customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTOReturn.setCustomerUrl(CustomerController.CUSTOMER_BASE_URL + savedCustomer.getId());

        return customerDTOReturn;
    }
}
