package com.pavan.security.services.implt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pavan.security.services.UserService;
import com.pavan.security.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpt implements UserService {
     
	private final UserRepository userRepository;
	
	@Override
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username)
			{
				return userRepository.findByEmail(username)
						.orElseThrow(()->new UsernameNotFoundException("User not found"));
			}
		};
	}
	
	
}
