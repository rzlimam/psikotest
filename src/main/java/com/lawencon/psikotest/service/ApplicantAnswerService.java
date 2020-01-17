package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.ApplicantAnswerDao;
import com.lawencon.psikotest.entity.ApplicantAnswer;

@Service
public class ApplicantAnswerService {
	
	@Autowired
	private ApplicantAnswerDao aaDao;
	
	public List<ApplicantAnswer> getAll(){
		List<ApplicantAnswer> list = aaDao.getAll();
		return list;
	}
	
	public void save(ApplicantAnswer aa) {
		aaDao.save(aa);
	}
	
	public void update(ApplicantAnswer aa) {
		aaDao.save(aa);
	}
	
	public void delete(String id) {
		aaDao.delete(id);
	}
	
	public ApplicantAnswer findById(String id) {
		ApplicantAnswer aa = aaDao.findById(id);
		return aa;
	}

}
