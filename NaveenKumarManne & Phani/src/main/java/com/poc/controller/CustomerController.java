package com.poc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.model.Customer;
import com.poc.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	private final Logger logger = Logger.getLogger(this.getClass());

	@Secured({ "ADMIN" })
	@PostMapping(value = "/saveCustomer", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> save(@RequestBody Customer customer) {
		logger.info("inside CustomerController save() :" + customer);
		Customer save = customerRepository.save(customer);
		if (save != null) {
			return new ResponseEntity<>(save, HttpStatus.OK);
		} else
			return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
	}
}
