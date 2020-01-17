package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.QuestionTypeDao;
import com.lawencon.psikotest.entity.QuestionType;

@Service("qtService")
public class QuestionTypeService {
	
	@Autowired
	private QuestionTypeDao qtDao;
	
	@Autowired
	private AnswerTypeService atService;
	
	public QuestionType findById(String id) {
		QuestionType qt = qtDao.findById(id);
		return qt;
	}
	
	
	public List<QuestionType> getAll(){
		List<QuestionType> list = qtDao.getAll();
		return list;
	}
	
	public QuestionType findByBk(String qtTitle) {
		QuestionType qt = qtDao.findByBk(qtTitle);
		return qt;
	}
	
	public void insert(QuestionType qt) throws Exception {
		try {
			valIdNull(qt);
			valBkNotNull(qt);
			valBkNotExist(qt);
			ValASExist(qt.getAnswerType().getAnswerTypeId());
			ValNonBk(qt);
			qtDao.save(qt);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(QuestionType qt) throws Exception {
		try {
			valIdNotNull(qt);
			ValIdExist(qt.getQuestionTypeId());
			valBkNotNull(qt);
			ValBkNotChange(qt);
			ValNonBk(qt);
			qtDao.save(qt);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			qtDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
			//Id null
			private Exception valIdNull(QuestionType qt) throws Exception{
				if(qt.getQuestionTypeId()!=null) {
					throw new Exception("QuestionType Id not null");
				}
				return null;
			}
			
			//Id null
			private Exception valIdNotNull(QuestionType qt) throws Exception{
				if(qt.getQuestionTypeId()==null || qt.getQuestionTypeId().trim().equals("")) {
					throw new Exception("QuestionType Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(QuestionType qt) throws Exception{
				if(qt.getQuestionTypeTitle() ==null || qt.getQuestionTypeTitle().trim().equals("")) {
					throw new Exception("QuestionType Title is null");
				}
				return null;
			}
			
			//BK not exist
			private Exception valBkNotExist(QuestionType qt) throws Exception{
				if(findByBk(qt.getQuestionTypeTitle())!=null) {
					throw new Exception("QuestionType Title is Exist");
				}
				return null;
			}
			
			//NonBk not null
			private Exception ValNonBk(QuestionType qt) throws Exception {
				if(qt.getAnswerType()==null || qt.getAnswerType().getAnswerTypeId().trim().equals("")) {
					throw new Exception("Answer Type is empty");
				} else if(qt.getIsActive()==null) {
					throw new Exception("Active state is empty");
				}
				return null;
			}
			
			//Bk not change
			private Exception ValBkNotChange(QuestionType qt) throws Exception {
				if(!findById(qt.getQuestionTypeId()).getQuestionTypeId().equalsIgnoreCase(qt.getQuestionTypeTitle())) {
					throw new Exception("QuestionType Code is Change");
				}
				return null;
			}
			
			//Id Exist
			private Exception ValIdExist(String id) throws Exception {
				if(findById(id)==null) {
					throw new Exception("Id is not Exist");
				}
				return null;
			}
			
			//Answer Type Exist
			private Exception ValASExist(String id) throws Exception {
				if(atService.findById(id)==null) {
					throw new Exception("Answer Type is not Exist");
				}
				return null;
			}

}
