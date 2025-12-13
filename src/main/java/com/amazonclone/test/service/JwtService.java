package com.amazonclone.test.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.expiration}")
    private long EXPIRATION; // e.g., 86400000 (1 day)
    private Key getSignKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
    //    0.11.5
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())   // set key
                .build()                       // build parser
                .parseClaimsJws(token)         // parse signed JWT
                .getBody();                    // get claims
    }
    // for Jwt 0.12 and spring 3.5
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(getSignKey())   // verify with your SecretKey
//                .build()
//                .parseSignedClaims(token)   // returns Jwt<Header, Claims>
//                .getPayload();
//    }
    // ✅ Extract username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private boolean isExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // ✅ Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token)) && !isExpired(token);
    }

    // ✅ Generate token and add role
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        //    claims.put("role", role);
        return createToken(claims,userName);
    }

    
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
