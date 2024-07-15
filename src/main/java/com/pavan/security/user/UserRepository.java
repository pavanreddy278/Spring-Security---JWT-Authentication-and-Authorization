package com.pavan.security.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   //Optional<User> findbyUsername(String email);

	Optional<User> findByEmail(String email);

	User findByRole(Role role);
}
