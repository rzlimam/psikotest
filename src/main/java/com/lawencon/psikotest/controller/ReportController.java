package com.lawencon.psikotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.service.ReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {
	
	@Autowired
	private ReportService report;
	
	@GetMapping("/report1")
	public ResponseEntity<?> correctPerPackage(){
		List<POJOStats> stats = null;
		try {
			stats =  report.correctPerPackage();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(stats);
	}
	
	@GetMapping("/report2")
	public ResponseEntity<?> falsePerPackage(){
		List<POJOStats> stats = null;
		try {
			stats =  report.falsePerPackage();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(stats);
	}

}
