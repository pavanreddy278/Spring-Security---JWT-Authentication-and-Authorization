package com.pavan.security.services;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	String extractUsername(String token);
	boolean isTokenValid(String token, UserDetails userDetails);
	String generateToken(UserDetails userDetails);
	String generateRefreshToken(
	          Map<String, Object> extraClaims,
	          UserDetails userDetails
	  );
}
