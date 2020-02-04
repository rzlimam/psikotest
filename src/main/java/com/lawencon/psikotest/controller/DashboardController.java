package com.lawencon.psikotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.POJODashboard;
import com.lawencon.psikotest.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboard;
	
	@GetMapping
	public ResponseEntity<?> getDashboard(){
		POJODashboard dash = null;
		try {
			dash = dashboard.getDashboard();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dash);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<?> recentTest(){
		List<POJODashboard> dash = null;
		try {
			dash = dashboard.recentTest();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dash);
	}
	
	@GetMapping("/ranking")
	public ResponseEntity<?> ranking(){
		List<POJODashboard> dash = null;
		try {
			dash = dashboard.ranking();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(dash);
	}

}
