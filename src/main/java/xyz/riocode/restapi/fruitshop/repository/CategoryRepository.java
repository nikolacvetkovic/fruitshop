package xyz.riocode.restapi.fruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.restapi.fruitshop.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
