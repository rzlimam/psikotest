package com.lawencon.psikotest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.psikotest.dao.QuestionDao;
import com.lawencon.psikotest.entity.Image;
import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.QuestionData;
import com.lawencon.psikotest.entity.QuestionType;
import com.lawencon.psikotest.entity.SearchQuestion;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.ValidAnswer;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao qDao;
	
	@Autowired
	private QuestionTypeService qtService;
	
	@Autowired
	private UserService userService;
	
//	private static String paths = "E:\\Rizal\\Boothcamp\\psikotest\\src\\main\\resources\\img\\";
	
	@Value("${upload.folder-dir}")
	private String paths;
	
	public List<Question> getAll(){
		List<Question> list = qDao.getAll();
		return list;
	}
	
	public Question findById(String id) {
		Question question = qDao.findById(id);
		return question;
	}
	
	public List<Question> findQueston(SearchQuestion sq) {
		List<Question> question = qDao.findQuestion(sq);
		return question;
	}
	
	public void insert(Question question) throws Exception {
		try {
//			valIdNull(question);
//			valBkNotNull(question);
//			ValQTExist(question.getQuestionType().getQuestionTypeId());
//			ValUserExist(question.getUser().getUserId());
//			ValNonBk(question);
			
			qDao.save(question);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(Question question) throws Exception {
		try {
//			valIdNotNull(question);
//			ValIdExist(question.getQuestionId());
//			valBkNotNull(question);
//			ValNonBk(question);
			qDao.save(question);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Question insertImg(String questionType,
			String questionTitle,
			String quest,
			MultipartFile[] image,
			MultipartFile choiceA,
			MultipartFile choiceB,
			MultipartFile choiceC,
			MultipartFile choiceD,
			MultipartFile[] ans,
			String userId,
			String isActive) throws Exception {
		
		Question question = new Question();
		QuestionData data = new QuestionData();
		QuestionType qtype = new QuestionType(); 
		ValidAnswer answer = new ValidAnswer();
		User user = new User();
		
		List<String> img = new ArrayList<String>();
			
		//create directory
//			Path p = Paths.get(paths);
//			if(!Files.exists(p)) {
//				try {
//					Files.createDirectories(p);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			
			for (MultipartFile i : image) {
				byte[] byteImage = i.getBytes();
				Image pic = new Image();
				pic.setData(byteImage);
				pic.setFileName(i.getOriginalFilename());
				pic.setType(i.getContentType());
				Path path = Paths.get(paths + i.getOriginalFilename());
				Files.write(path, byteImage);
				img.add(path.toString());
			}
		
			
			//set question type
			qtype.setQuestionTypeId(questionType);
			
			
			//set question data
			data.setQuestion(quest);
			data.setQuestionImage(img);
			
			//set choiceA
			byte[] byteA = choiceA.getBytes();
			Image picA = new Image();
			picA.setData(byteA);
			picA.setFileName(choiceA.getOriginalFilename());
			picA.setType(choiceA.getContentType());
			Path pathA = Paths.get(paths + choiceA.getOriginalFilename());
			Files.write(pathA, byteA);
			data.setChoiceA(pathA.toString());
			
			//set choiceB
			byte[] byteB = choiceA.getBytes();
			Image picB = new Image();
			picB.setData(byteB);
			picB.setFileName(choiceB.getOriginalFilename());
			picB.setType(choiceB.getContentType());
			Path pathB = Paths.get(paths + choiceB.getOriginalFilename());
			Files.write(pathB, byteB);
			data.setChoiceB(pathB.toString());
			
			//set choiceC
			byte[] byteC = choiceC.getBytes();
			Image picC = new Image();
			picC.setData(byteC);
			picC.setFileName(choiceC.getOriginalFilename());
			picC.setType(choiceC.getContentType());
			Path pathC = Paths.get(paths + choiceC.getOriginalFilename());
			Files.write(pathC, byteC);
			data.setChoiceC(pathC.toString());
			
			//set choiceD
			byte[] byteD = choiceD.getBytes();
			Image picD = new Image();
			picD.setData(byteD);
			picD.setFileName(choiceD.getOriginalFilename());
			picD.setType(choiceD.getContentType());
			Path pathD = Paths.get(paths + choiceD.getOriginalFilename());
			Files.write(pathD, byteD);
			data.setChoiceD(pathD.toString());
			
			//set valid answer
			for (MultipartFile a : ans) {
				byte[] ansImage = a.getBytes();
				Image ansImg = new Image();
				ansImg.setData(ansImage);
				ansImg.setFileName(a.getOriginalFilename());
				ansImg.setType(a.getContentType());
				Path path = Paths.get(paths + a.getOriginalFilename());
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
			question.setQuestionTitle(questionTitle);
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
//			valIdNull(question);
//			valBkNotNull(question);
//			ValQTExist(question.getQuestionType().getQuestionTypeId());
//			ValUserExist(question.getUser().getUserId());
//			ValNonBk(question);
			qDao.save(question);
		
		return question;
	}
	
//	public Question updateImg(String id,
//			String questionType,
//			String questionTitle,
//			String quest,
//			MultipartFile[] image,
//			MultipartFile choiceA,
//			MultipartFile choiceB,
//			MultipartFile choiceC,
//			MultipartFile choiceD,
//			MultipartFile[] ans,
//			String userId,
//			String isActive) throws Exception {
//		
//		//find question by id
//		Question question = findById(id);
//		
//		QuestionData data = new QuestionData();
//		QuestionType qtype = new QuestionType(); 
//		ValidAnswer answer = new ValidAnswer();
//		User user = new User();
//		
//		List<String> img = new ArrayList<String>();
//		
//		try {
//			for (MultipartFile i : image) {
//				String ext = FilenameUtils.getExtension(i.getOriginalFilename());
//				ValExentension(ext);
//				byte[] byteImage = i.getBytes();
//				Path path = Paths.get(paths + i.getOriginalFilename());
//				Files.write(path, byteImage);
//				img.add(path.toString());
//			}
//			
//			//set question type
//			qtype.setQuestionTypeId(questionType);
//			
//			//set question data
//			data.setQuestion(quest);
//			data.setQuestionImage(img);
//			
//			//set choiceA
//			String extA = FilenameUtils.getExtension(choiceA.getOriginalFilename());
//			ValExentension(extA);
//			byte[] byteA = choiceA.getBytes();
//			Path pathA = Paths.get(paths + choiceA.getOriginalFilename());
//			Files.write(pathA, byteA);
//			data.setChoiceA(pathA.toString());
//			
//			//set choiceB
//			String extB = FilenameUtils.getExtension(choiceB.getOriginalFilename());
//			ValExentension(extB);
//			byte[] byteB = choiceB.getBytes();
//			Path pathB = Paths.get(paths + choiceB.getOriginalFilename());
//			Files.write(pathB, byteB);
//			data.setChoiceB(pathB.toString());
//			
//			//set choiceC
//			String extC = FilenameUtils.getExtension(choiceC.getOriginalFilename());
//			ValExentension(extC);
//			byte[] byteC = choiceC.getBytes();
//			Path pathC = Paths.get(paths + choiceC.getOriginalFilename());
//			Files.write(pathC, byteC);
//			data.setChoiceC(pathC.toString());
//			
//			//set choiceD
//			String extD = FilenameUtils.getExtension(choiceD.getOriginalFilename());
//			ValExentension(extD);
//			byte[] byteD = choiceD.getBytes();
//			Path pathD = Paths.get(paths + choiceD.getOriginalFilename());
//			Files.write(pathD, byteD);
//			data.setChoiceD(pathD.toString());
//			
//			//set valid answer
//			for (MultipartFile a : ans) {
//				String extAns = FilenameUtils.getExtension(a.getOriginalFilename());
//				ValExentension(extAns);
//				byte[] ansImage = a.getBytes();
//				Path path = Paths.get(paths + a.getOriginalFilename());
//				Files.write(path, ansImage);
//				if(answer.getValidAnswer1()==null) {
//					answer.setValidAnswer1(path.toString());
//				} else if (answer.getValidAnswer2()==null) {
//					answer.setValidAnswer2(path.toString());
//				}
//			}
//			
//			//set user
//			user.setUserId(userId);
//			
//			//set question
//			question.setQuestionType(qtype);
//			question.setData(data);
//			question.setAnswer(answer);
//			question.setUser(user);
//			
//			//set isActive
//			boolean as = Boolean.parseBoolean(isActive);
//			question.setIsActive(as);
//			
//			//set date of question
//			Date date = new Date();
//			question.setDateOfQuestion(date);
//			
//			//save question to database
//			valIdNotNull(question);
//			ValIdExist(question.getQuestionId());
//			valBkNotNull(question);
//			ValNonBk(question);
//			qDao.save(question);
//		}catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//		
//		return question;
//	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			qDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
			//Id null
			private Exception valIdNull(Question question) throws Exception{
				if(question.getQuestionId()!=null) {
					throw new Exception("Question Id not null");
				}
				return null;
			}
			
			//Id null
			private Exception valIdNotNull(Question question) throws Exception{
				if(question.getQuestionId()==null || question.getQuestionId().trim().equals("")) {
					throw new Exception("Question Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(Question question) throws Exception{
				if(question.getData()==null) {
					throw new Exception("Question Data is null");
				}
				return null;
			}
			
			//BK not exist
			private Exception valBkNotExist(Question question) throws Exception{
				if(question.getData()!=null) {
					throw new Exception("Question Code is Exist");
				}
				return null;
			}
			
			//NonBk not null
			private void ValNonBk(Question question) throws Exception {
				if(question.getQuestionType()==null || question.getQuestionType().getQuestionTypeId().trim().equals("")) {
					throw new Exception("Question Type is empty");
				} else if(question.getAnswer()==null || question.getAnswer().getValidAnswer1().trim().equals("")) {
					throw new Exception("Answer is empty");
				} else if(question.getUser()==null || question.getUser().getUserId().trim().equals("")) {
					throw new Exception("User is empty");
				} else if(question.getIsActive()==null) {
					throw new Exception("Active state is empty");
				} else if(question.getDateOfQuestion()==null) {
					throw new Exception("Date of question is empty");
				}
			}
			
			//Bk not change
//			private Exception ValBkNotChange(Question question) throws Exception {
//				if() {
//					throw new Exception("Question Code is Change");
//				}
//				return null;
//			}
			
			//Id Exist
			private Exception ValIdExist(String id) throws Exception {
				if(findById(id)==null) {
					throw new Exception("Id is not Exist");
				}
				return null;
			}
			
			//Question Type Exist
			private Exception ValQTExist(String id) throws Exception {
				if(qtService.findById(id)==null) {
					throw new Exception("Question Type is not Exist");
				}
				return null;
			}
			
			//User Exist
			private Exception ValUserExist(String id) throws Exception {
				if(userService.findById(id)==null) {
					throw new Exception("User is not Exist");
				}
				return null;
			}
			
			//Check Extension
			private Exception ValExentension(String ext) throws Exception {
				if(ext.equalsIgnoreCase("jpg") ||
						ext.equalsIgnoreCase("jpeg") || 
						ext.equalsIgnoreCase("png")) {
					return null;
				}
				throw new Exception("File harus berupa jpg atau png");
			}
	
}
