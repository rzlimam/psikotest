package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.AnswerTypeDao;
import com.lawencon.psikotest.entity.AnswerType;

@Service("atService")
public class AnswerTypeService {
	
	@Autowired
	private AnswerTypeDao atDao;
	
	public AnswerType findById(String id) {
		AnswerType at = atDao.findById(id);
		return at;
	}
	
	public AnswerType findByCode(String code) {
		AnswerType at = atDao.findByCode(code);
		return at;
	}
	
	public List<AnswerType> getAll(){
		List<AnswerType> list = atDao.getAll();
		return list;
	}
	
	public void insert(AnswerType at) throws Exception {
		try {
			valIdNull(at);
			valBkNotNull(at);
			valBkNotExist(at);
			ValNonBk(at);
			atDao.save(at);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void update(AnswerType at) throws Exception {
		try {
			valIdNotNull(at);
			ValIdExist(at.getAnswerTypeId());
			valBkNotNull(at);
			ValBkNotChange(at);
			ValNonBk(at);
			atDao.save(at);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			atDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
		//Id null
		private Exception valIdNull(AnswerType at) throws Exception{
			if(at.getAnswerTypeId()!=null) {
				throw new Exception("AnswerType Id not null");
			}
			return null;
		}
		
		//Id null
		private Exception valIdNotNull(AnswerType at) throws Exception{
			if(at.getAnswerTypeId()==null || at.getAnswerTypeId().trim().equals("")) {
				throw new Exception("AnswerType Id null");
			}
			return null;
		}
		
		//BK not null
		private Exception valBkNotNull(AnswerType at) throws Exception{
			if(at.getCodeAnswerType() ==null || at.getCodeAnswerType().trim().equals("")) {
				throw new Exception("AnswerType Code is null");
			}
			return null;
		}
		
		//BK not exist
		private Exception valBkNotExist(AnswerType at) throws Exception{
			if(findByCode(at.getCodeAnswerType())!=null) {
				throw new Exception("AnswerType Code is Exist");
			}
			return null;
		}
		
		//NonBk not null
		private Exception ValNonBk(AnswerType at) throws Exception {
			if(at.getTypeOfAnswer()==null || at.getTypeOfAnswer().trim().equals("")) {
				throw new Exception("Type of Answer is empty");
			} else if(at.getAmountOfAnswer()==null || at.getAmountOfAnswer().trim().equals("")) {
				throw new Exception("Amount of Answer is empty");
			}
			return null;
		}
		
		//Bk not change
		private Exception ValBkNotChange(AnswerType at) throws Exception {
			if(!findById(at.getAnswerTypeId()).getAnswerTypeId().equalsIgnoreCase(at.getCodeAnswerType())) {
				throw new Exception("AnswerType Code is Change");
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


}
