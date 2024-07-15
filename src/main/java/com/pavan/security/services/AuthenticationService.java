package com.pavan.security.services;

import com.pavan.security.dto.JwtAuthenticationResponse;
import com.pavan.security.dto.RefreshTokenRequest;
import com.pavan.security.dto.SignInRequest;
import com.pavan.security.dto.SignUpRequest;
import com.pavan.security.user.User;

public interface AuthenticationService {
          
	JwtAuthenticationResponse signin(SignInRequest signInRequest);
	User signUp(SignUpRequest signUpRequest);  
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
