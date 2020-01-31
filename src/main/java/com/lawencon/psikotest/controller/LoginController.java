package com.lawencon.psikotest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.config.JwtTokenUtil;
import com.lawencon.psikotest.entity.JwtResponse;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.service.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encrypt;
	
	@PostMapping("")
	public ResponseEntity<?> insertUser(@RequestBody Map<String, String> account) throws Exception {
//		UserList user;
//		try {
//			user = userService.login(account.get("username"), account.get("password"));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//		
//		return ResponseEntity.status(HttpStatus.OK).body(user);
		User user = new User();
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

}
