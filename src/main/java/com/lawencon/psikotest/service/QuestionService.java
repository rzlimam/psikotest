package com.lawencon.psikotest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lawencon.psikotest.dao.QuestionDao;
import com.lawencon.psikotest.entity.Image;
import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.QuestionData;
import com.lawencon.psikotest.entity.QuestionType;
import com.lawencon.psikotest.entity.SearchQuestion;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.ValidAnswer;
import com.lawencon.psikotest.exception.MyFileNotFoundException;

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
	private Path paths;
	
	public List<Question> getAll(){
		List<Question> list = qDao.getAll();
		return list;
	}
	
	public Question findById(String id) {
		Question question = qDao.findById(id);
		return question;
	}
	
	public List<Question> getQuestion(String qtId) {
		List<Question> question = qDao.getQuestion(qtId);
		return question;
	}
	
	public List<Question> findQueston(SearchQuestion sq) {
		List<Question> question = qDao.findQuestion(sq);
		return question;
	}
	
	public void insert(Question question) throws Exception {
		try {
			question.setIsActive(true);
			valIdNull(question);
			valBkNotNull(question);
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
			
//		//create directory
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
				Path path = Paths.get(paths + i.getOriginalFilename());
				Files.write(path, byteImage);
				
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/download/image/")
		                .path(i.getOriginalFilename())
		                .toUriString();
				img.add(fileDownloadUri);
			}
		
			
			//set question type
			qtype.setQuestionTypeId(questionType);
			
			
			//set question data
			data.setQuestion(quest);
			data.setQuestionImage(img);
			
			//set choiceA
			byte[] byteA = choiceA.getBytes();
			Path pathA = Paths.get(paths + choiceA.getOriginalFilename());
			Files.write(pathA, byteA);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/download/image/")
	                .path(choiceA.getOriginalFilename())
	                .toUriString();
			data.setChoiceA(fileDownloadUri);
			
			//set choiceB
			byte[] byteB = choiceB.getBytes();
			Path pathB = Paths.get(paths + choiceB.getOriginalFilename());
			Files.write(pathB, byteB);
			fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/download/image/")
	                .path(choiceB.getOriginalFilename())
	                .toUriString();
			data.setChoiceB(fileDownloadUri);
			
			//set choiceC
			byte[] byteC = choiceC.getBytes();
			Path pathC = Paths.get(paths + choiceC.getOriginalFilename());
			Files.write(pathC, byteC);
			fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/download/image/")
	                .path(choiceC.getOriginalFilename())
	                .toUriString();
			data.setChoiceC(fileDownloadUri);
			
			//set choiceD
			byte[] byteD = choiceD.getBytes();
			Path pathD = Paths.get(paths + choiceD.getOriginalFilename());
			Files.write(pathD, byteD);
			fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/download/image/")
	                .path(choiceD.getOriginalFilename())
	                .toUriString();
			data.setChoiceD(fileDownloadUri);
			
			//set valid answer
			for (MultipartFile a : ans) {
				byte[] ansImage = a.getBytes();
				Path path = Paths.get(paths + a.getOriginalFilename());
				Files.write(path, ansImage);
				fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/download/image/")
		                .path(a.getOriginalFilename())
		                .toUriString();
				if(answer.getValidAnswer1()==null) {
					answer.setValidAnswer1(fileDownloadUri);
				} else if (answer.getValidAnswer2()==null) {
					answer.setValidAnswer2(fileDownloadUri);
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
			valIdNull(question);
			valBkNotNull(question);
			ValQTExist(question.getQuestionType().getQuestionTypeId());
			ValUserExist(question.getUser().getUserId());
//			ValNonBk(question);
			qDao.save(question);
		
		return question;
	}
	
	public Question updateImg(String id,
			String questionType,
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
		
		//find question by id
		Question question = findById(id);
		
		QuestionData data = new QuestionData();
		QuestionType qtype = new QuestionType(); 
		ValidAnswer answer = new ValidAnswer();
		User user = new User();
		
		List<String> img = new ArrayList<String>();
		
		try {
			for (MultipartFile i : image) {
//				String ext = FilenameUtils.getExtension(i.getOriginalFilename());
//				ValExentension(ext);
				byte[] byteImage = i.getBytes();
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
//			String extA = FilenameUtils.getExtension(choiceA.getOriginalFilename());
//			ValExentension(extA);
			byte[] byteA = choiceA.getBytes();
			Path pathA = Paths.get(paths + choiceA.getOriginalFilename());
			Files.write(pathA, byteA);
			data.setChoiceA(pathA.toString());
			
			//set choiceB
//			String extB = FilenameUtils.getExtension(choiceB.getOriginalFilename());
//			ValExentension(extB);
			byte[] byteB = choiceB.getBytes();
			Path pathB = Paths.get(paths + choiceB.getOriginalFilename());
			Files.write(pathB, byteB);
			data.setChoiceB(pathB.toString());
			
			//set choiceC
//			String extC = FilenameUtils.getExtension(choiceC.getOriginalFilename());
//			ValExentension(extC);
			byte[] byteC = choiceC.getBytes();
			Path pathC = Paths.get(paths + choiceC.getOriginalFilename());
			Files.write(pathC, byteC);
			data.setChoiceC(pathC.toString());
			
			//set choiceD
//			String extD = FilenameUtils.getExtension(choiceD.getOriginalFilename());
//			ValExentension(extD);
			byte[] byteD = choiceD.getBytes();
			Path pathD = Paths.get(paths + choiceD.getOriginalFilename());
			Files.write(pathD, byteD);
			data.setChoiceD(pathD.toString());
			
			//set valid answer
			for (MultipartFile a : ans) {
//				String extAns = FilenameUtils.getExtension(a.getOriginalFilename());
//				ValExentension(extAns);
				byte[] ansImage = a.getBytes();
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
			valIdNotNull(question);
			ValIdExist(question.getQuestionId());
//			valBkNotNull(question);
//			ValNonBk(question);
			qDao.save(question);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return question;
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			Question question = qDao.findById(id);
			question.setIsActive(false);
			qDao.save(question);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	 public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = paths.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MyFileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new MyFileNotFoundException("File not found " + fileName, ex);
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
