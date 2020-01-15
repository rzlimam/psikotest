package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.ApplicantAnswer;

@Repository("applicantAnswerDao")
public class ApplicantAnswerDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ApplicantAnswer> getAll() {
		List<ApplicantAnswer> list = super.entityManager
				.createQuery("From ApplicantAnswer")
				.getResultList();
		return list;
	}
	
	@Transactional
	public ApplicantAnswer save(ApplicantAnswer applicantAns) {
		return super.entityManager.merge(applicantAns);
	}
	
	@Transactional
	public void delete(String id) {
		ApplicantAnswer applicantAns = findById(id);
		super.entityManager.remove(applicantAns);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ApplicantAnswer findById(String id) {
		List<ApplicantAnswer> list = super.entityManager
				.createQuery("From ApplicantAnswer where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return new ApplicantAnswer();
		else 
			return (ApplicantAnswer)list.get(0);
	}

}
