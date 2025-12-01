package com.amazonclone.test.controller;

import com.amazonclone.test.model.Customer;
import com.amazonclone.test.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    CustomerService customerService;
    @PostMapping("/public/adduser")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
     Customer customer1=   customerService.saveCustomer(customer);
        return ResponseEntity.ok().body(customer1);
    }
    @GetMapping("/public/allUser")
    public ResponseEntity<List<Customer>> allUserList(){
        return customerService.getAllUser();
    }
 }
