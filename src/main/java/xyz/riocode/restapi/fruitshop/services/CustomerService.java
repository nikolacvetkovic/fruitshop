package xyz.riocode.restapi.fruitshop.services;

import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);

}
