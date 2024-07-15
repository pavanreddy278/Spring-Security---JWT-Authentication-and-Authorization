package com.pavan.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.security.dto.JwtAuthenticationResponse;
import com.pavan.security.dto.RefreshTokenRequest;
import com.pavan.security.dto.SignInRequest;
import com.pavan.security.dto.SignUpRequest;
import com.pavan.security.services.AuthenticationService;
import com.pavan.security.user.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
   private final AuthenticationService authenticationService;
   
   @PostMapping("/signup")
   public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest)
   {
	   return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
   }
   
   @PostMapping("/signin")
   public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest)
   {
	   return ResponseEntity.ok(authenticationService.signin(signInRequest));
   }
   
   @PostMapping("/refresh")
   public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest)
   {
	   return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
   }

}
