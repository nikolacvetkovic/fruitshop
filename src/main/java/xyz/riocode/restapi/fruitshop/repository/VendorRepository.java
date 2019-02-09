package xyz.riocode.restapi.fruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.restapi.fruitshop.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
