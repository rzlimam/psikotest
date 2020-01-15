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

import com.lawencon.psikotest.entity.QuestionAssign;
import com.lawencon.psikotest.service.QuestionAssignService;

@RestController
@RequestMapping("/questionassign")
public class QuestionAssignController {
	
	@Autowired
	private QuestionAssignService qaService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<QuestionAssign> list = null;
		try {
			list =  qaService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody QuestionAssign qa) {
		try {
			qaService.insert(qa);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Inserted");
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody QuestionAssign qa) {
		try {
			qaService.update(qa);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			qaService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}
