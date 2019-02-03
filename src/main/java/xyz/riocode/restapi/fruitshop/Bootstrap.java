package xyz.riocode.restapi.fruitshop;

import org.springframework.boot.CommandLineRunner;
import xyz.riocode.restapi.fruitshop.domain.Category;
import xyz.riocode.restapi.fruitshop.repository.CategoryRepository;

public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category();
        category1.setName("Fruits");

        Category category2 = new Category();
        category2.setName("Dried");

        Category category3 = new Category();
        category3.setName("Fresh");

        Category category4 = new Category();
        category4.setName("Exotic");

        Category category5 = new Category();
        category5.setName("nuts");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);

        System.out.println("Data loaded: " + categoryRepository.count());
    }
}
