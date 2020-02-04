package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<DetailApplicantAnswer> findByHAA(String id) {
		List<DetailApplicantAnswer> list = super.entityManager
				.createQuery("From DetailApplicantAnswer where "
						+ "headerApplicantAnswer.applicantAnswerId=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return list;
	}
	
	
	@Transactional
	public BigInteger sumPoint(String appAnswer) {
		Query query  = super.entityManager
				.createNativeQuery("Select sum(point) FROM group2.tbl_detail_applicant_answer "
						+ "WHERE applicant_answer_id = '" + appAnswer + "'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countQuestion(String appAnswer) {
		Query query  = super.entityManager
				.createNativeQuery("Select count(*) FROM group2.tbl_detail_applicant_answer "
						+ "WHERE applicant_answer_id = '" + appAnswer + "'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countQuestionNotEssay(String appAnswer) {
		Query query  = super.entityManager
				.createNativeQuery("Select count(*) FROM group2.tbl_detail_applicant_answer daa"
						+ " join group2.tbl_m_package_detail pd on"
						+ " daa.package_question_id = pd.package_question_id"
						+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
						+ " join group2.tbl_m_question q on pd.question_id = q.question_id"
						+ " join group2.tbl_question_type qt on q.question_type_id = qt.question_type_id"
						+ " WHERE applicant_answer_id = '" + appAnswer + "' and"
						+ " qt.question_type_id <> 'asdas'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}

}
