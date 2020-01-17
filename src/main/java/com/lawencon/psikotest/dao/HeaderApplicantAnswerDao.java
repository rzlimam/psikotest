package com.lawencon.psikotest.dao;

import java.util.Date;
import java.util.List;

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
		List<HeaderApplicantAnswer> list = super.entityManager
				.createQuery("From HeaderApplicantAnswer where date_of_answer=:doa"
						+ " and user_id=:userId")
				.setParameter("doa", doa)
				.setParameter("userId",userId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (HeaderApplicantAnswer)list.get(0);
	}

}
