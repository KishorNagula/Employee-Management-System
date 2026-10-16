package com.employee.management.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

	private final String SECRET_KEY =
	        "my-secret-key-for-employee-management-project-2026"; //secret key

	private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; //token expiration.
	
	public String generateToken(String username) {

	    return Jwts.builder()
	            .subject(username)
	            .issuedAt(new Date())
	            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	            .signWith(key)
	            .compact();
	}
	
	public String extractUsername(String token) {

	    return Jwts.parser()
	            .verifyWith(key)
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}
	
	public boolean isTokenValid(String token, String username) {

	    String extractedUsername = extractUsername(token);

	    return extractedUsername.equals(username)
	            && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {

	    Date expiration = Jwts.parser()
	            .verifyWith(key)
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getExpiration();

	    return expiration.before(new Date());
	}
}