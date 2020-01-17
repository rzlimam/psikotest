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

import com.lawencon.psikotest.entity.QuestionType;
import com.lawencon.psikotest.service.QuestionTypeService;

@RestController
@RequestMapping("/questiontype")
public class QuestionTypeController {
	
	@Autowired
	private QuestionTypeService qtService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<QuestionType> list = null;
		try {
			list =  qtService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody QuestionType qt) {
		try {
			qtService.insert(qt);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(qt);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody QuestionType qt) {
		try {
			qtService.update(qt);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(qt);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			qtService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}
