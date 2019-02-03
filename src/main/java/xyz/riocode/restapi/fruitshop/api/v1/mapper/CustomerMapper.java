package xyz.riocode.restapi.fruitshop.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.riocode.restapi.fruitshop.api.v1.model.CustomerDTO;
import xyz.riocode.restapi.fruitshop.domain.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

}
