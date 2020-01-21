package com.lawencon.psikotest.controller;

import java.math.BigInteger;
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

import com.lawencon.psikotest.entity.DetailApplicantAnswer;
import com.lawencon.psikotest.entity.HeaderApplicantAnswer;
import com.lawencon.psikotest.entity.PackageDetail;
import com.lawencon.psikotest.service.DetailApplicantAnswerService;
import com.lawencon.psikotest.service.HeaderApplicantAnswerService;
import com.lawencon.psikotest.service.PackageDetailService;
import com.lawencon.psikotest.service.QuestionService;

@RestController
@RequestMapping("/daa")
public class DetailApplicantAnsController {
	
	@Autowired
	private DetailApplicantAnswerService daaService;
	
	@Autowired
	private PackageDetailService pdService; 
	
	@Autowired
	private HeaderApplicantAnswerService haaService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<DetailApplicantAnswer> list = null;
		try {
			list =  daaService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody List<DetailApplicantAnswer> daa) {
		try {
			for (DetailApplicantAnswer d : daa) {
				//insert data to database
				daaService.insert(d);
			}
			
			//find applicant answer header
			HeaderApplicantAnswer haa = haaService.findById(daa.get(0).getHeaderApplicantAnswer().getApplicantAnswerId());
			
			//get Result test
			daaService.getResult(daa.get(0), haa);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(daa);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody DetailApplicantAnswer daa) {
		try {
			daaService.update(daa);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(daa);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			daaService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}
