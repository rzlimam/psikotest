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

import com.lawencon.psikotest.entity.POJOPackage;
import com.lawencon.psikotest.entity.Packages;
import com.lawencon.psikotest.service.*;

@RestController
@RequestMapping("/packages")
@CrossOrigin("*")
public class PackageController {
	
	@Autowired
	private PackageService packageService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<Packages> packages = null;
//		List<POJOPackage> packages = null;
		try {
			packages =  packageService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(packages);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody Packages packages) {
		try {
			packageService.insert(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(packages);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Packages packages) {
		try {
			packageService.update(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(packages);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			packageService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Packages Deleted");
	}


}
