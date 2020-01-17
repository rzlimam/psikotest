package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.PointTypeDao;
import com.lawencon.psikotest.entity.PointForEachType;

@Service("pptService")
public class PointPerTypeService {
	
	@Autowired
	private PointTypeDao ptDao;
	
	@Autowired
	private QuestionTypeService qtService;
	
	@Autowired
	private UserService userService;
	
	public List<PointForEachType> getAll(){
		List<PointForEachType> list = ptDao.getAll();
		return list;
	}

	public PointForEachType findById(String id) {
		PointForEachType pt = ptDao.findById(id);
		return pt;
	}

	public PointForEachType findByBk(String qtId, String userId) {
		PointForEachType pt = ptDao.findByBk(qtId, userId);
		return pt;
	}
	
	public void insert(PointForEachType ppt) throws Exception {
		try {
			valIdNull(ppt);
			valBkNotNull(ppt);
			valBkNotExist(ppt);
			ValQTExist(ppt.getQuestionType().getQuestionTypeId());
			ValUserExist(ppt.getUser().getUserId());
			ValNonBk(ppt);
			ptDao.save(ppt);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(PointForEachType ppt) throws Exception {
		try {
			valIdNotNull(ppt);
			ValIdExist(ppt.getPointTypeId());
			valBkNotNull(ppt);
			ValBkNotChange(ppt);
			ValNonBk(ppt);
			ptDao.save(ppt);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			ptDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	//Validasi
	
	//Id null
	private Exception valIdNull(PointForEachType ppt) throws Exception{
		if(ppt.getPointTypeId()!=null) {
			throw new Exception("QuestionType Id not null");
		}
		return null;
	}
	
	//validasi 
	
		//Id null
		private Exception valIdNotNull(PointForEachType ppt) throws Exception{
			if(ppt.getPointTypeId()==null) {
				throw new Exception("Id null");
			}
			return null;
		}
		
		//BK not null
		private Exception valBkNotNull(PointForEachType ppt) throws Exception{
			if(ppt.getQuestionType().getQuestionTypeId() ==null || 
					ppt.getQuestionType().getQuestionTypeId().trim().equals("")) {
				throw new Exception("Question type is null");
			} else if(ppt.getUser().getUserId()==null || ppt.getUser().getUserId().trim().equals("")) {
				throw new Exception("User type is null");
			}
			return null;
		}
		
		//BK not exist
		private Exception valBkNotExist(PointForEachType ppt) throws Exception{
			if(findByBk(ppt.getQuestionType().getQuestionTypeId(), ppt.getUser().getUserId())!=null) {
				throw new Exception("BK is Exist");
			}
			return null;
		}
		
		//NonBk not null
		private Exception ValNonBk(PointForEachType ppt) throws Exception {
			if(ppt.getTotapPointForEachType()==null) {
				throw new Exception("Total point is empty");
			}
			return null;
		}
		
		//Bk not change
		private Exception ValBkNotChange(PointForEachType ppt) throws Exception {
			if(!(findById(ppt.getPointTypeId()).getQuestionType().getQuestionTypeId()
					.equalsIgnoreCase(ppt.getQuestionType().getQuestionTypeId()) ||
					findById(ppt.getPointTypeId()).getUser().getUserId()
					.equalsIgnoreCase(ppt.getUser().getUserId()))) {
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
		
		//Question Type Exist
		private Exception ValQTExist(String id) throws Exception {
			if(qtService.findById(id)==null) {
				throw new Exception("Answer Type is not Exist");
			}
			return null;
		}
		
		//Question Type Exist
		private Exception ValUserExist(String id) throws Exception {
			if(userService.findById(id)==null) {
				throw new Exception("Answer Type is not Exist");
			}
			return null;
		}

}
