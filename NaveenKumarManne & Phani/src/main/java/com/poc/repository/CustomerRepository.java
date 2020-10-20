package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
