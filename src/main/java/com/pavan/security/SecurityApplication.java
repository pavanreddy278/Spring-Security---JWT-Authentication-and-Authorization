package com.pavan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pavan.security.user.Role;
import com.pavan.security.user.User;
import com.pavan.security.user.UserRepository;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{

	@Autowired
    private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	
	public void run(String... args) {
		User adminAccount=userRepository.findByRole(Role.ADMIN);
		if(adminAccount==null)
		{
			User user=new User();
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

}
