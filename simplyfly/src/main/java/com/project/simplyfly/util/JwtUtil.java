package com.project.simplyfly.util;

import java.security.Key;
import java.util.Date;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Configuration
public class JwtUtil {
/*
 * In this class we need to create following methods 
 * 1. Method to Create JWT Token 
 * 2. Method to verify the Token 
 * */
	
	private static final String secretKey = "LMS_HEX_MAY_7867486754876768463678";
	private static final long expirationTimeInMills=43200000; //12 hrs 
	Key key; 
	
	{
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	/* Method to Generate JWT Token*/
	public String createToken(String email){
		System.out.println("I am in create token method");
		return Jwts.builder()
	                .setSubject(email)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMills)) 
	                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()),SignatureAlgorithm.HS256)
	                .compact();
		}
	
	/* Method to verify the Token */
	private Key getSigningKey(){
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
 
	public boolean verifyToken(String token, String email) {
		String extractedEmail = Jwts.parserBuilder()
									.setSigningKey(getSigningKey())
									 .build()
									 .parseClaimsJws(token)
									 .getBody()
									 .getSubject();
		Date expirationDate = Jwts.parserBuilder()
									.setSigningKey(getSigningKey())
									 .build()
									 .parseClaimsJws(token)
									 .getBody()
									 .getExpiration();
		
		return extractedEmail.equals(email) && new Date().before(expirationDate); 			
	}

	public String extractUsername(String token) {
		return  Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				 .build()
				 .parseClaimsJws(token)
				 .getBody()
				 .getSubject(); 
	}
	   
}