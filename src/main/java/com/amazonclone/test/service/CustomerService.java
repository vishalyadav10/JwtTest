package com.amazonclone.test.service;

import com.amazonclone.test.model.Customer;
import com.amazonclone.test.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService  {
    private CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder; // inject encoder

    public Customer saveCustomer(Customer customer) {
        // Hash the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepo.save(customer);
        return customer;
    }

    public ResponseEntity<List<Customer>> getAllUser() {
       List<Customer> customers = customerRepo.findAll();
       return  ResponseEntity.status(200).body(customers);
    }
}
