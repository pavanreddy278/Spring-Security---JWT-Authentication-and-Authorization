package com.pavan.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
	   @GetMapping
	   public ResponseEntity<String> sayHello()
	   {
		   return ResponseEntity.ok("hello user");
	   }

}
