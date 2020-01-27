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

import com.lawencon.psikotest.entity.*;
import com.lawencon.psikotest.service.*;

@RestController
@RequestMapping("/packagedetail")
@CrossOrigin("*")
public class PackageDetailController {
	
	@Autowired
	private PackageDetailService pacDetailService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<PackageDetail> pacDetail = null;
		try {
			pacDetail =  pacDetailService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(pacDetail);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		PackageDetail pacDetail = null;
		try {
			pacDetail =  pacDetailService.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(pacDetail);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody List<PackageDetail> packDetail) {
		try {
			for(PackageDetail pac:packDetail) {
				pacDetailService.insert(pac);
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(packDetail);
	}
	
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody PackageDetail pacDetail) {
		try {
			pacDetailService.update(pacDetail);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(pacDetail);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			pacDetailService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Package Detail Deleted");
	}


}
