package com.lawencon.psikotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.POJOMail;
import com.lawencon.psikotest.entity.Profile;
import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.Role;
import com.lawencon.psikotest.entity.SearchQuestion;
import com.lawencon.psikotest.entity.SearchUser;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.UserList;
import com.lawencon.psikotest.service.MailService;
import com.lawencon.psikotest.service.ProfileService;
import com.lawencon.psikotest.service.RoleService;
import com.lawencon.psikotest.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
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
	public ResponseEntity<?> getAllRecruiter(){
		List<UserList> user = null;
		try {
			user =  userService.getAllRecruiter();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		UserList user = null;
		try {
			user =  userService.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/candidate")
	public ResponseEntity<?> getAllCandidate(){
		List<UserList> user = null;
		try {
			user =  userService.getAllCandidate();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name){
		List<UserList> user = null;
		try {
			user =  userService.findByName(name);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email){
		UserList user = null;
		try {
			user =  userService.findByEmail(email);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody SearchUser su){
		List<UserList> list = null;
		try {
			list =  userService.findCandidate(su);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PostMapping("")
	public ResponseEntity<?> register(@RequestBody Profile profile) {
		User user = new User();
		try {
			String password = userService.getPassword();
			profile.setIsActive(true);
			Profile newProfile = profileService.insert(profile);
			Role role = roleService.findByCode("CAN");
			user.setPassword(password);
			user.setProfile(newProfile);
			user.setRole(role);
			user.setIsActive(true);
			User us = userService.insert(user);
			POJOMail mail = new POJOMail();
			mail.setName(profile.getProfileName());
			mail.setEmail(profile.getEmail());
			mail.setPassword(us.getPassword());
			mailService.sendAccount(mail);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insert(@RequestBody User user) {
		try {
			userService.insert(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("/forgotpassword/{email}")
	public ResponseEntity<?> forgotpassword(@PathVariable String email) {
		UserList user = null;
		try {
			user = userService.findByEmail(email);
			String password = userService.getPassword();
			
			User newUser = new User();
			newUser.setUserId(user.getUserId());
			newUser.setProfile(user.getProfile());
			newUser.setRole(user.getRole());
			newUser.setPassword(password);
			newUser.setIsActive(true);
			
			userService.update(newUser);
			
			POJOMail mail = new POJOMail();
			mail.setEmail(newUser.getProfile().getEmail());
			mail.setName(newUser.getProfile().getProfileName());
			mail.setPassword(newUser.getPassword());
			
			mailService.forgotPassword(mail);
			
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
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}

}
