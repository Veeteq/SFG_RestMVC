package com.wojnarowicz.sfg.restmvc.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.restmvc.domain.Category;
import com.wojnarowicz.sfg.restmvc.domain.Customer;
import com.wojnarowicz.sfg.restmvc.repositories.CategoryRepository;
import com.wojnarowicz.sfg.restmvc.repositories.CustomerRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category Data Loaded = " + categoryRepository.count() );
    }
    
    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Jack");
        customer1.setLastName("Black");
        customerRepository.save(customer1);
        
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Vanessa");
        customer2.setLastName("Gray");
        customerRepository.save(customer2);
        
        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("David");
        customer3.setLastName("Winter");
        customerRepository.save(customer3);

        System.out.println("Customer Data Loaded = " + customerRepository.count() );
    }

}
