package com.lawencon.psikotest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.HeaderApplicantAnswerDao;
import com.lawencon.psikotest.entity.HeaderApplicantAnswer;

@Service("haaService")
public class HeaderApplicantAnswerService {
	
	@Autowired
	private HeaderApplicantAnswerDao haDao;
	
	@Autowired
	private UserService userService;
	
	public List<HeaderApplicantAnswer> getAll(){
		List<HeaderApplicantAnswer> list = haDao.getAll();
		return list;
	}
	
	public HeaderApplicantAnswer findById(String id) {
		HeaderApplicantAnswer haa = haDao.findById(id);
		return haa;
	}
	
	public HeaderApplicantAnswer findByBk(Date doa, String userId) {
		HeaderApplicantAnswer haa = haDao.findByBk(doa, userId);
		return haa;
	}
	
	public void insert(HeaderApplicantAnswer haa) throws Exception {
		try {
			valIdNull(haa);
			valBkNotNull(haa);
			valBkNotExist(haa);
			ValUserExist(haa.getUser().getUserId());
			ValNonBk(haa);
			haDao.save(haa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(HeaderApplicantAnswer haa) throws Exception {
		try {
			valIdNotNull(haa);
			ValIdExist(haa.getApplicantAnswerId());
			valBkNotNull(haa);
			ValBkNotChange(haa);
			ValNonBk(haa);
			haDao.save(haa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			haDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
		//Id null
		private Exception valIdNull(HeaderApplicantAnswer haa) throws Exception{
			if(haa.getApplicantAnswerId()!=null) {
				throw new Exception("Header Applicant Answer Id not null");
			}
			return null;
		}
		
		//Id null
		private Exception valIdNotNull(HeaderApplicantAnswer haa) throws Exception{
			if(haa.getApplicantAnswerId()==null || haa.getApplicantAnswerId().trim().equals("")) {
				throw new Exception("HeaderApplicantAnswer Id is null");
			}
			return null;
		}
		
		//BK not null
		private Exception valBkNotNull(HeaderApplicantAnswer haa) throws Exception{
			if(haa.getApplicantAnswerId() ==null) {
				throw new Exception("BK is null");
			}
			return null;
		}
		
		//BK not exist
		private Exception valBkNotExist(HeaderApplicantAnswer haa) throws Exception{
			if(findByBk(haa.getDateOfAnswer(), haa.getUser().getUserId())!=null) {
				throw new Exception("HeaderApplicantAnswer is Exist");
			}
			return null;
		}
		
		//NonBk not null
		private Exception ValNonBk(HeaderApplicantAnswer haa) throws Exception {
			if(haa.getTotalPoint()==null) {
				throw new Exception("Total point is empty");
			}
			return null;
		}
		
		//Bk not change
		private Exception ValBkNotChange(HeaderApplicantAnswer haa) throws Exception {
			String tgl = haa.getDateOfAnswer().toString();
			if(!(findById(haa.getApplicantAnswerId()).getApplicantAnswerId()
					.equalsIgnoreCase(haa.getUser().getUserId()) || 
					 findById(haa.getApplicantAnswerId()).getApplicantAnswerId()
					 .equalsIgnoreCase(tgl))) {
				throw new Exception("BK is Change");
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
		
		//User Exist
		private Exception ValUserExist(String id) throws Exception {
			if(userService.findById(id)==null) {
				throw new Exception("Answer Type is not Exist");
			}
			return null;
		}
	

}
