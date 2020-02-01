package com.lawencon.psikotest.dao;

import java.math.BigInteger;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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

}
