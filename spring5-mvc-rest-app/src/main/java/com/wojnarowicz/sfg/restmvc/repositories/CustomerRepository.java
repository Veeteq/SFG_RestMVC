package com.wojnarowicz.sfg.restmvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.restmvc.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
