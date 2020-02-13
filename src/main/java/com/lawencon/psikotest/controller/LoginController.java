package com.lawencon.psikotest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.config.JwtTokenUtil;
import com.lawencon.psikotest.entity.POJOMail;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.UserList;
import com.lawencon.psikotest.service.MailService;
import com.lawencon.psikotest.service.UserService;

@RestController
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> insertUser(@RequestBody Map<String, String> account) throws Exception {

		UserList user = new UserList();
		List<Object> session = new ArrayList<Object>();
		user = userService.findByEmail(account.get("username"));
		session.add(user);
		
		authenticate(account.get("username"), account.get("password"));
		
		final UserDetails userDetails = userService
				.loadUserByUsername(account.get("username"));
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		session.add(token);
			
		return ResponseEntity.ok(session);
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
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

}
