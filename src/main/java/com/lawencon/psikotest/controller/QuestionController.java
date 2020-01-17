package com.lawencon.psikotest.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.QuestionData;
import com.lawencon.psikotest.entity.QuestionType;
import com.lawencon.psikotest.entity.SearchQuestion;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.ValidAnswer;
import com.lawencon.psikotest.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	private static String UPLOAD_DIR = "E://Rizal//Boothcamp//psikotest//src//main//resources//img//";
	
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
			questionService.insert(question);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Inserted");
	}
	
	@PostMapping("/image")
	public ResponseEntity<?> insertImage(@RequestPart String qt,
			@RequestPart String quest,
			@RequestPart MultipartFile[] image,
			@RequestPart String choiceA,
			@RequestPart String choiceB,
			@RequestPart String choiceC,
			@RequestPart String choiceD,
			@RequestPart MultipartFile[] ans,
			@RequestPart String userId,
			@RequestPart String isActive) {
		Question question = new Question();
		QuestionData data = new QuestionData();
		QuestionType qtype = new QuestionType(); 
		ValidAnswer answer = new ValidAnswer();
		User user = new User();
		List<String> img = new ArrayList<String>();
		try {
			for (MultipartFile i : image) {
				byte[] byteImage = i.getBytes();
				Path path = Paths.get(UPLOAD_DIR + i.getOriginalFilename());
				Files.write(path, byteImage);
				img.add(path.toString());
			}
			//set question type
			qtype.setQuestionTypeId(qt);
			
			//set question data
			data.setQuestion(quest);
			data.setQuestionImage(img);
			data.setChoiceA(choiceA);
			data.setChoiceB(choiceB);
			data.setChoiceC(choiceC);
			data.setChoiceD(choiceD);
			
			//set valid answer
			for (MultipartFile a : ans) {
				byte[] ansImage = a.getBytes();
				Path path = Paths.get(UPLOAD_DIR + a.getOriginalFilename());
				Files.write(path, ansImage);
				if(answer.getValidAnswer1()==null) {
					answer.setValidAnswer1(path.toString());
				} else if (answer.getValidAnswer2()==null) {
					answer.setValidAnswer2(path.toString());
				}
			}
			
			//set user
			user.setUserId(userId);
			
			//set question
			question.setQuestionType(qtype);
			question.setData(data);
			question.setAnswer(answer);
			question.setUser(user);
			
			//set isActive
			boolean as = Boolean.parseBoolean(isActive);
			question.setIsActive(as);
			
			//set date of question
			Date date = new Date();
			question.setDateOfQuestion(date);
			
			//save question to database
			questionService.insert(question);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Inserted");
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Question question) {
		try {
			questionService.update(question);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Updated");
	}
	
	@PutMapping("/image")
	public ResponseEntity<?> updateImage(@RequestPart String id,
			@RequestPart String qt,
			@RequestPart String quest,
			@RequestPart MultipartFile[] image,
			@RequestPart String choiceA,
			@RequestPart String choiceB,
			@RequestPart String choiceC,
			@RequestPart String choiceD,
			@RequestPart MultipartFile[] ans,
			@RequestPart String userId,
			@RequestPart String isActive) {
		
		//find question by id
		Question question = questionService.findById(id);
		
		QuestionData data = new QuestionData();
		QuestionType qtype = new QuestionType(); 
		ValidAnswer answer = new ValidAnswer();
		User user = new User();
		List<String> img = new ArrayList<String>();
		try {
			for (MultipartFile i : image) {
				byte[] byteImage = i.getBytes();
				Path path = Paths.get(UPLOAD_DIR + i.getOriginalFilename());
				Files.write(path, byteImage);
				img.add(path.toString());
			}
			//set question type
			qtype.setQuestionTypeId(qt);
			
			//set question data
			data.setQuestion(quest);
			data.setQuestionImage(img);
			data.setChoiceA(choiceA);
			data.setChoiceB(choiceB);
			data.setChoiceC(choiceC);
			data.setChoiceD(choiceD);
			
			//set valid answer
			for (MultipartFile a : ans) {
				byte[] ansImage = a.getBytes();
				Path path = Paths.get(UPLOAD_DIR + a.getOriginalFilename());
				Files.write(path, ansImage);
				if(answer.getValidAnswer1()==null) {
					answer.setValidAnswer1(path.toString());
				} else if (answer.getValidAnswer2()==null) {
					answer.setValidAnswer2(path.toString());
				}
			}
			
			//set user
			user.setUserId(userId);
			
			//set question
			question.setQuestionType(qtype);
			question.setData(data);
			question.setAnswer(answer);
			question.setUser(user);
			
			//set isActive
			boolean as = Boolean.parseBoolean(isActive);
			question.setIsActive(as);
			
			//set date of question
			Date date = new Date();
			question.setDateOfQuestion(date);
			
			
			//save question to database
			questionService.insert(question);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Inserted");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable String id) {
		try {
			questionService.delete(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
	
	@GetMapping("/search")
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
