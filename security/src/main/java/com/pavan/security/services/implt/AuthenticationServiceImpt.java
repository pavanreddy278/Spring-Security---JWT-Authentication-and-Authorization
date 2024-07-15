package com.pavan.security.services.implt;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pavan.security.dto.JwtAuthenticationResponse;
import com.pavan.security.dto.RefreshTokenRequest;
import com.pavan.security.dto.SignInRequest;
import com.pavan.security.dto.SignUpRequest;
import com.pavan.security.services.AuthenticationService;
import com.pavan.security.services.JwtService;
import com.pavan.security.user.Role;
import com.pavan.security.user.User;
import com.pavan.security.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpt implements AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	
	public User signUp(SignUpRequest signUpRequest)
	{
		User user=new User();
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstname());
		user.setLastname(signUpRequest.getLastname());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setRole(Role.USER);
		return userRepository.save(user);
	}
	
	
	public JwtAuthenticationResponse signin(SignInRequest signInRequest)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
		var user=userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid user"));
		var token=jwtService.generateToken(user);
		var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
		JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();           
		jwtAuthenticationResponse.setToken(token);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		return jwtAuthenticationResponse;
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
	{
		String userEmail=jwtService.extractUsername(refreshTokenRequest.getToken());
		User user=userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user))
		{
			var jwt=jwtService.generateToken(user);
			JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();           
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthenticationResponse;

		}
		
		return null;
	}


}
