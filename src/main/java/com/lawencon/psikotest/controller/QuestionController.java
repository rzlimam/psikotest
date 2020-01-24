package com.lawencon.psikotest.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.SearchQuestion;
import com.lawencon.psikotest.service.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll(){
		List<Question> list = null;
		try {
			list =  questionService.getAll();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody Question question) {
		try {
			Date date = new Date();
			question.setDateOfQuestion(date);
			questionService.insert(question);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(question);
	}
	
	@PostMapping("/image")
	public ResponseEntity<?> insertImage(@RequestPart String qt,
			@RequestPart String title,
			@RequestPart String quest,
			@RequestPart MultipartFile[] image,
			@RequestPart MultipartFile choiceA,
			@RequestPart MultipartFile choiceB,
			@RequestPart MultipartFile choiceC,
			@RequestPart MultipartFile choiceD,
			@RequestPart MultipartFile[] ans,
			@RequestPart String userId,
			@RequestPart String isActive) {
		Question question;
		try {
			question = questionService.insertImg(qt, title, quest, 
					image, choiceA, choiceB, choiceC, choiceD, 
					ans, userId, isActive);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(question);
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Question question) {
		try {
			questionService.update(question);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(question);
	}
	
//	@PutMapping("/image")
//	public ResponseEntity<?> updateImage(@RequestPart String id,
//			@RequestPart String qt,
//			@RequestPart String title,
//			@RequestPart String quest,
//			@RequestPart MultipartFile[] image,
//			@RequestPart MultipartFile choiceA,
//			@RequestPart MultipartFile choiceB,
//			@RequestPart MultipartFile choiceC,
//			@RequestPart MultipartFile choiceD,
//			@RequestPart MultipartFile[] ans,
//			@RequestPart String userId,
//			@RequestPart String isActive) {
//		
//		Question question;
//		try {
//			question = questionService.updateImg(id, qt, title, quest, 
//					image, choiceA, choiceB, choiceC, choiceD, 
//					ans, userId, isActive);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(question);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			questionService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody SearchQuestion sq){
		List<Question> list = null;
		try {
			list =  questionService.findQueston(sq);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
