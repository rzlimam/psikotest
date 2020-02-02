package com.lawencon.psikotest.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.entity.POJOStats1;
import com.lawencon.psikotest.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {
	
	@Autowired
	private ReportService report;
	
	@GetMapping("/report1/{format}")
	public ResponseEntity<?> correctPerPackage(@PathVariable String format, HttpServletRequest request) 
			throws JRException, IOException {
		
		String fileName = report.correctPerPackage(format);
		
		// Load file as Resource
        Resource resource = report.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    	
	}
	
	@GetMapping("/report2/{format}")
	public ResponseEntity<?> falsePerPackage(@PathVariable String format, HttpServletRequest request) 
			throws JRException, IOException{
		
		String fileName = report.falsePerPackage(format);
		
		// Load file as Resource
        Resource resource = report.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/report3/{format}")
	public ResponseEntity<?> mostCorrect(@PathVariable String format, HttpServletRequest request) 
			throws JRException, IOException{
		
		String fileName = report.mostCorrect(format);
		
		// Load file as Resource
        Resource resource = report.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/report4/{format}")
	public ResponseEntity<?> mostFalse(@PathVariable String format, HttpServletRequest request) 
			throws JRException, IOException{
		
		String fileName = report.mostFalse(format);
		
		// Load file as Resource
        Resource resource = report.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/report5/{format}")
	public ResponseEntity<?> easiestPackage(@PathVariable String format, HttpServletRequest request) 
			throws JRException, IOException{
		
		String fileName = report.easiestPackage(format);
		
		// Load file as Resource
        Resource resource = report.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/report6/{format}")
	public ResponseEntity<?> hardestPackage(@PathVariable String format, HttpServletRequest request) 
			throws JRException, IOException{
		
		String fileName = report.hardestPackage(format);
		
		// Load file as Resource
        Resource resource = report.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/report/{format}")
	public ResponseEntity<?> generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		try {
			report.falsePerPackage(format);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		Object obj = "Report generated";
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

}
