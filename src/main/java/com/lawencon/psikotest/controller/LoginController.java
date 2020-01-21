package com.lawencon.psikotest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.UserList;
import com.lawencon.psikotest.service.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public ResponseEntity<?> insertUser(@RequestBody Map<String, String> account) {
		UserList user;
		try {
			user = userService.login(account.get("username"), account.get("password"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
