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

import com.lawencon.psikotest.entity.Profile;
import com.lawencon.psikotest.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<Profile> profile = null;
		try {
			profile =  profileService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		Profile profile = null;
		try {
			profile =  profileService.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody Profile profile) {
		try {
			profileService.insert(profile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Profile profile) {
		try {
			profileService.update(profile);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			profileService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profileService.findById(id));
	}
	
	@GetMapping("/name/{search}")
	public ResponseEntity<?> getName(@PathVariable("search") String search){
		List<Profile> profile = null;
		try {
			profile =  profileService.findByName(search);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@GetMapping("/email/{search}")
	public ResponseEntity<?> getEmail(@PathVariable("search") String search){
		List<Profile> profile = null;
		try {
			profile =  profileService.findByEmail(search);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}
	
	@GetMapping("/phone/{search}")
	public ResponseEntity<?> getPhone(@PathVariable("search") String search){
		List<Profile> profile = null;
		try {
			profile =  profileService.findByPhone(search);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(profile);
	}


}
