package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.QuestionDao;
import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.SearchQuestion;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao qDao;
	
	@Autowired
	private QuestionTypeService qtService;
	
	@Autowired
	private UserService userService;
	
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
			valIdNull(question);
			valBkNotNull(question);
			ValQTExist(question.getQuestionType().getQuestionTypeId());
			ValUserExist(question.getUser().getUserId());
			ValNonBk(question);
			qDao.save(question);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(Question question) throws Exception {
		try {
			valIdNotNull(question);
			ValIdExist(question.getQuestionId());
			valBkNotNull(question);
			qDao.save(question);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
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
			private Exception ValNonBk(Question question) throws Exception {
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
				return null;
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
	
}
