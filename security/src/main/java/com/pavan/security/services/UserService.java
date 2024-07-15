package com.pavan.security.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	
	public UserDetailsService userDetailsService();
}
