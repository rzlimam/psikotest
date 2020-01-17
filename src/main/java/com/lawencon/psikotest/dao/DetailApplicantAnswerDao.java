package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.DetailApplicantAnswer;

@Repository("detailAnsDao")
public class DetailApplicantAnswerDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailApplicantAnswer> getAll() {
		List<DetailApplicantAnswer> list = super.entityManager
				.createQuery("From DetailApplicantAnswer")
				.getResultList();
		return list;
	}
	
	@Transactional
	public DetailApplicantAnswer save(DetailApplicantAnswer applicantAns) {
		return super.entityManager.merge(applicantAns);
	}
	
	@Transactional
	public void delete(String id) {
		DetailApplicantAnswer applicantAns = findById(id);
		super.entityManager.remove(applicantAns);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailApplicantAnswer findById(String id) {
		List<DetailApplicantAnswer> list = super.entityManager
				.createQuery("From DetailApplicantAnswer where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (DetailApplicantAnswer)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public DetailApplicantAnswer findByBk(String appAnswer, String packQuestion) {
		List<DetailApplicantAnswer> list = super.entityManager
				.createQuery("From DetailApplicantAnswer where applicant_answer_id=:aaId "
						+ "and package_question_id=:pqId")
				.setParameter("aaId", appAnswer)
				.setParameter("pqId", packQuestion)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (DetailApplicantAnswer)list.get(0);
	}

}