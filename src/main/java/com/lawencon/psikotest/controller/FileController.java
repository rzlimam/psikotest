package com.lawencon.psikotest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.psikotest.service.QuestionService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/download")
@CrossOrigin
public class FileController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/image/{file}")
	public ResponseEntity<?> getImage(@PathVariable String file, HttpServletRequest request) 
			throws JRException, IOException{
				
		// Load file as Resource
        Resource resource = questionService.loadFileAsResource(file);

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

}
