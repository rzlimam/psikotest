package com.lawencon.psikotest.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.HeaderApplicantAnswer;

@Repository("headerAnsDao")
public class HeaderApplicantAnswerDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<HeaderApplicantAnswer> getAll() {
		List<HeaderApplicantAnswer> list = super.entityManager
				.createQuery("From HeaderApplicantAnswer")
				.getResultList();
		return list;
	}
	
	@Transactional
	public HeaderApplicantAnswer save(HeaderApplicantAnswer applicantAns) {
		return super.entityManager.merge(applicantAns);
	}
	
	@Transactional
	public void delete(String id) {
		HeaderApplicantAnswer applicantAns = findById(id);
		super.entityManager.remove(applicantAns);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public HeaderApplicantAnswer findById(String id) {
		List<HeaderApplicantAnswer> list = super.entityManager
				.createQuery("From HeaderApplicantAnswer where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (HeaderApplicantAnswer)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public HeaderApplicantAnswer findByBk(Date doa,String userId) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		String todayStr = formatter.format(doa);
		Date dateNow = null;
		try {
			dateNow = formatter.parse(todayStr);
		} catch (Exception e) {
			dateNow = new Date();
		}
		
		List<HeaderApplicantAnswer> list = super.entityManager
				.createQuery("From HeaderApplicantAnswer where date_of_answer=:doa"
						+ " and user_id=:userId")
				.setParameter("doa", dateNow)
				.setParameter("userId",userId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (HeaderApplicantAnswer)list.get(0);
	}
	

}
