package xyz.riocode.restapi.fruitshop.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.riocode.restapi.fruitshop.domain.Category;
import xyz.riocode.restapi.fruitshop.domain.Customer;
import xyz.riocode.restapi.fruitshop.domain.Vendor;
import xyz.riocode.restapi.fruitshop.repository.CategoryRepository;
import xyz.riocode.restapi.fruitshop.repository.CustomerRepository;
import xyz.riocode.restapi.fruitshop.repository.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
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
        category5.setName("Nuts");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);

        System.out.println("Categories loaded: " + categoryRepository.count());

        Customer customer1 = new Customer();
        customer1.setFirstName("Ana");
        customer1.setLastName("Zdravkovic");

        Customer customer2 = new Customer();
        customer2.setFirstName("Nikola");
        customer2.setLastName("Cvetkovic");

        Customer customer3 = new Customer();
        customer3.setFirstName("Marija");
        customer3.setLastName("Zdravkovic");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        System.out.println("Customers loaded: " + customerRepository.count());

        Vendor vendor1 = new Vendor();
        vendor1.setName("Samsung");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Huawei");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Vox");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);

        System.out.println("Vendors loaded: " + vendorRepository.count());
    }
}
