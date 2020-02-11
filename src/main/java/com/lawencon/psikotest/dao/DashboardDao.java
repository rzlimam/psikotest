package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.HeaderApplicantAnswer;

@Repository
public class DashboardDao extends EntityDao {
	
	@Transactional
	public BigInteger countPass() {
		Query query  = super.entityManager
				.createNativeQuery("select count(status) from group2.tbl_header_applicant_answer thaa"
						+ " where thaa.status = 'Lulus'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countFail() {
		Query query  = super.entityManager
				.createNativeQuery("select count(status) from group2.tbl_header_applicant_answer thaa"
						+ " where thaa.status <> 'Lulus'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countQuestion() {
		Query query  = super.entityManager
				.createNativeQuery("select count(*) from group2.tbl_m_question");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countPackage() {
		Query query  = super.entityManager
				.createNativeQuery("select count(*) from group2.tbl_m_package");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<HeaderApplicantAnswer> recentTest() {
		List<HeaderApplicantAnswer> recent = super.entityManager
				.createQuery("From HeaderApplicantAnswer"
						+ " ORDER BY dateOfAnswer DESC")
				.setMaxResults(10)
				.getResultList();
		return recent;
	}
	
	@SuppressWarnings("unchecked")
	public List<HeaderApplicantAnswer> ranking() {
		List<HeaderApplicantAnswer> ranking = super.entityManager
				.createQuery("From HeaderApplicantAnswer"
						+ " ORDER BY score DESC")
				.setMaxResults(10)
				.getResultList();
		return ranking;
	}

}
