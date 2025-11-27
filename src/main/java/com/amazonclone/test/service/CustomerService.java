package com.amazonclone.test.service;

import com.amazonclone.test.model.Customer;
import com.amazonclone.test.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService  {
    private CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder; // inject encoder

    public void saveCustomer(Customer customer) {
        // Hash the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepo.save(customer);
    }
}
