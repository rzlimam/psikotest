package com.lawencon.psikotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.UserList;
import com.lawencon.psikotest.service.UserService;

@RestController
@RequestMapping("/demo")
@CrossOrigin
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllRecruiter(){
		List<UserList> user = null;
		try {
			user =  userService.getAllRecruiter();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
