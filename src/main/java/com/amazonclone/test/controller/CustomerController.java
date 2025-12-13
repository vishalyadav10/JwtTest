package com.amazonclone.test.controller;

import com.amazonclone.test.model.Customer;
import com.amazonclone.test.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerRepo customerRepo;

    private final PasswordEncoder passwordEncoder; // inject enco
    @PostMapping("/public/adduser")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        // Hash the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepo.save(customer);
        return ResponseEntity.ok().body(customer);
    }
}
