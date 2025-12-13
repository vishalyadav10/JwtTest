package com.amazonclone.test.config;

import com.amazonclone.test.model.Customer;
import com.amazonclone.test.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Customer customer= customerRepo.findByUsername(username)
    .orElseThrow(() -> new UsernameNotFoundException(username));
       return  new CustomerDetails(customer);
    }
}

