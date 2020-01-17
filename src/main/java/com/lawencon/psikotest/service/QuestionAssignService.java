package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.QuestionAssignDao;
import com.lawencon.psikotest.entity.QuestionAssign;

@Service("qaService")
public class QuestionAssignService {
	
	@Autowired
	private QuestionAssignDao qaDao;
	
	@Autowired
	private PackageService packService;
	
	@Autowired
	private UserService userService;
	
	public List<QuestionAssign> getAll(){
		List<QuestionAssign> list = qaDao.getAll();
		return list;
	}

	public QuestionAssign findById(String id) {
		QuestionAssign qa = qaDao.findById(id);
		return qa;
	}
	
	public QuestionAssign findByBk(QuestionAssign qas) {
		QuestionAssign qa = qaDao.findByBk(qas);
		return qa;
	}
	
	public void insert(QuestionAssign qa) throws Exception {
		try {
			valIdNull(qa);
			valBkNotNull(qa);
			valBkNotExist(qa);
			ValPackageExist(qa.getPackagee().getPackageId());
			ValUserExist(qa.getUser().getUserId());
			qaDao.save(qa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(QuestionAssign qa) throws Exception {
		try {
			valIdNotNull(qa);
			ValIdExist(qa.getAssignQuestionId());
			valBkNotNull(qa);
			qaDao.save(qa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			qaDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
			//Id null
			private Exception valIdNull(QuestionAssign qa) throws Exception{
				if(qa.getAssignQuestionId()!=null) {
					throw new Exception("QuestionAssign Id not null");
				}
				return null;
			}
			
			//Id null
			private Exception valIdNotNull(QuestionAssign qa) throws Exception{
				if(qa.getAssignQuestionId()==null || qa.getAssignQuestionId().trim().equals("")) {
					throw new Exception("QuestionAssign Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(QuestionAssign qa) throws Exception{
				if(qa.getPackagee()==null || qa.getPackagee().getPackageId().trim().equals("")) {
					throw new Exception("Package is null");
				} else if(qa.getUser()==null || qa.getUser().getUserId().trim().equals("")) {
					throw new Exception("User is null");
				}
				return null;
			}
			
			//BK not exist
			private Exception valBkNotExist(QuestionAssign qa) throws Exception{
				if(findByBk(qa) != null) {
					throw new Exception("QuestionAssign BK is Exist");
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
			
			//Package Exist
			private Exception ValPackageExist(String id) throws Exception {
				if(packService.findById(id)==null) {
					throw new Exception("Package is not Exist");
				}
				return null;
			}
			
			//Package Exist
			private Exception ValUserExist(String id) throws Exception {
				if(userService.findById(id)==null) {
					throw new Exception("User is not Exist");
				}
				return null;
			}


}
