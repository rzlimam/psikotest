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

import com.lawencon.psikotest.*;
import com.lawencon.psikotest.entity.AnswerType;
import com.lawencon.psikotest.service.*;

@RestController
@RequestMapping("/answerType")
public class AnswerTypeController {
	
	@Autowired
	private AnswerTypeService answerTypeService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<AnswerType> answerType = null;
		try {
			answerType =  answerTypeService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(answerType);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody AnswerType answerType) {
		try {
			answerTypeService.insert(answerType);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(answerType);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody AnswerType answerType) {
		try {
			answerTypeService.update(answerType);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(answerType);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			answerTypeService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Answer Type Deleted");
	}


}
