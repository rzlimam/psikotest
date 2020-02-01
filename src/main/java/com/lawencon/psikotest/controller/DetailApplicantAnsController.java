package com.lawencon.psikotest.controller;

import java.io.FileNotFoundException;
import java.util.Date;
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

import com.lawencon.psikotest.entity.DetailApplicantAnswer;
import com.lawencon.psikotest.entity.HeaderApplicantAnswer;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.service.DetailApplicantAnswerService;
import com.lawencon.psikotest.service.HeaderApplicantAnswerService;
import com.lawencon.psikotest.service.PackageDetailService;
import com.lawencon.psikotest.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/daa")
@CrossOrigin("*")
public class DetailApplicantAnsController {
	
	@Autowired
	private DetailApplicantAnswerService daaService;
	
	@Autowired
	private PackageDetailService pdService; 
	
	@Autowired
	private HeaderApplicantAnswerService haaService;
	
	@Autowired
	private ReportService report;
	
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		DetailApplicantAnswer list = null;
		try {
			list =  daaService.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/haa/{id}")
	public ResponseEntity<?> findByHAA(@PathVariable String id){
		List<DetailApplicantAnswer> list = null;
		try {
			list =  daaService.findByHAA(id);
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
			daaService.getResult(haa);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(daa);
	}
	
	@PostMapping("/insert/{id}")
	public ResponseEntity<?> insert(@RequestBody List<DetailApplicantAnswer> daa, @PathVariable String id) {
		try {
			HeaderApplicantAnswer header  = new HeaderApplicantAnswer();
			User user = new User();
			user.setUserId(id);
			header.setUser(user);
			Date date = new Date();
			header.setDateOfAnswer(date);
			HeaderApplicantAnswer head =  haaService.insert(header);
			for (DetailApplicantAnswer d : daa) {
				//insert data to database
				d.setHeaderApplicantAnswer(head);
				daaService.insert(d);
			}
			
			//find applicant answer header
			HeaderApplicantAnswer haa = haaService.findById(head.getApplicantAnswerId());
			
			//get Result test
			daaService.getResult(haa);
			
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
		Object obj = "Deleted";
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@GetMapping("/report/{id}/{format}")
	public ResponseEntity<?> generateReport(@PathVariable String id, @PathVariable String format) throws FileNotFoundException, JRException {
		try {
			report.exportReport(format, id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		Object obj = "Report generated";
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

}
