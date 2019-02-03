package xyz.riocode.restapi.fruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.restapi.fruitshop.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
