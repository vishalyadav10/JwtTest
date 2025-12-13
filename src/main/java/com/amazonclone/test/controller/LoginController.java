package com.amazonclone.test.controller;

import com.amazonclone.test.DTO.JwtRequest;
import com.amazonclone.test.DTO.JwtResponse;
import com.amazonclone.test.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/public/login")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            System.out.print("inside createAuthenticationToken");
          //   Authenticate the User
           Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
           if(authentication.isAuthenticated()) {
                String jwt = jwtService.generateToken(jwtRequest.getUsername());
                JwtResponse jwtResponse = new JwtResponse(jwt,200);
               return ResponseEntity.status(200).body(jwtResponse);
           }
        } catch (AuthenticationException e) {
            e.printStackTrace(); // see BadCredentials vs UsernameNotFound
            return ResponseEntity.status(401).body(new JwtResponse(null,400));
         //   throw new Exception("Incorrect username or password", e);
        }
        return ResponseEntity.status(401).body(new JwtResponse(null,400));

    }
}
