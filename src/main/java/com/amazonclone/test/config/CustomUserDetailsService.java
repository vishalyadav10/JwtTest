package com.amazonclone.test.config;

import com.amazonclone.test.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with: " + username);
        return customerRepo.findByUsername(username)
    .orElseThrow(() -> new UsernameNotFoundException(username));

    }
}

