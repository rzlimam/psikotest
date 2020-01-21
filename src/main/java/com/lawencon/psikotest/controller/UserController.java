package com.lawencon.psikotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.Profile;
import com.lawencon.psikotest.entity.Role;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.UserList;
import com.lawencon.psikotest.service.MailService;
import com.lawencon.psikotest.service.ProfileService;
import com.lawencon.psikotest.service.RoleService;
import com.lawencon.psikotest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired 
	private MailService mailService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<UserList> user = null;
		try {
			user =  userService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody Profile profile) {
		User user = new User();
		try {
			String password = userService.getPassword();
			Profile newProfile = profileService.insert(profile);
			Role role = roleService.findByCode("CAN");
			user.setPassword(password);
			user.setProfile(newProfile);
			user.setRole(role);
			user.setIsActive(true);
			userService.insert(user);
			String email = profile.getEmail();
			mailService.sendMail(email, password);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		try {
			userService.insert(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			userService.update(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			userService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
	}

}
