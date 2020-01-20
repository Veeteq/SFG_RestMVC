package com.wojnarowicz.sfg.restmvc.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;
import com.wojnarowicz.sfg.restmvc.domain.Category;
import com.wojnarowicz.sfg.restmvc.domain.Customer;
import com.wojnarowicz.sfg.restmvc.domain.Vendor;
import com.wojnarowicz.sfg.restmvc.repositories.CategoryRepository;
import com.wojnarowicz.sfg.restmvc.repositories.CustomerRepository;
import com.wojnarowicz.sfg.restmvc.repositories.VendorRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final AgentRepository agentRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository, AgentRepository agentRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
        loadAgents();
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

        System.out.println("Category Data Loaded = " + categoryRepository.count());
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

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Exotic Fruits Company");
        vendorRepository.save(vendor1);
        
        Vendor vendor2 = new Vendor();
        vendor2.setName("Western Tasty Fruits Ltd.");
        vendorRepository.save(vendor2);
        
        Vendor vendor3 = new Vendor();
        vendor3.setName("Home Fruits");
        vendorRepository.save(vendor3);
        
        System.out.println("Vendor Data Loaded = " + vendorRepository.count() );
    }
    
    private void loadAgents() {
        BsoDocument bso1 = new BsoDocument();
        //bso1.setId(1L);
        bso1.setNumber("050001");
        bso1.setSeries("6100");
        bso1.setStatus(BsoStatus.NEW);
        bso1.setType("40");
        
        BsoDocument bso2 = new BsoDocument();
        //bso2.setId(3L);
        bso2.setNumber("050002");
        bso2.setSeries("6100");
        bso2.setStatus(BsoStatus.NEW);
        bso2.setType("40");

        Agent agent1 = new Agent();
        agent1.setCode(Long.valueOf(1588426));
        agent1.setFirstName("Светлана");
        agent1.setLastName("Новикова");
        agent1.setLnr(Long.valueOf(1588426));
        agent1.setMiddleName("Владимировна");
        agent1.setSkk(Long.valueOf(16207320));
        agent1.addBso(bso1);
        agent1.addBso(bso2);
        
        System.out.println(agent1.getBsoDocuments().size());
        agentRepository.save(agent1);
        
        System.out.println("Agent Data Loaded = " + agentRepository.count());
    }
}
