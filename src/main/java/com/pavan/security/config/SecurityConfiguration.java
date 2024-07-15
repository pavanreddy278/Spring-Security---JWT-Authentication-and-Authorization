package com.pavan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pavan.security.services.UserService;
import com.pavan.security.user.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final UserService userService;
	//private final AuthenticationProvider authenticationProvider;


	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(req->req.requestMatchers("/api/v1/auth/**").permitAll()
		.requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
		.requestMatchers("/api/v1/user").hasAnyAuthority(Role.USER.name())
		.anyRequest().authenticated()
		) 
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userService.userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
    }
	
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	      return config.getAuthenticationManager();
	    }


}
