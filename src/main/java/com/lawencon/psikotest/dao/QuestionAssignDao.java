package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.QuestionAssign;

@Repository("questionAssignDao")
public class QuestionAssignDao extends EntityDao {
	
	@Transactional
	public void save(QuestionAssign qa) {
		super.entityManager.merge(qa);
	}
	
	@Transactional
	public void delete(String id) {
		QuestionAssign qa = findById(id);
		super.entityManager.remove(qa);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public QuestionAssign findById(String id) {
		List<QuestionAssign> list = super.entityManager
				.createQuery("From QuestionAssign where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (QuestionAssign)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<QuestionAssign> getAll() {
		List<QuestionAssign> list = super.entityManager
				.createQuery("From QuestionAssign")
				.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public QuestionAssign findByBk(QuestionAssign qa) {
		List<QuestionAssign> list = super.entityManager
				.createQuery("From QuestionAssign where package_id=:packageId "
						+ "and user_id=:userId")
				.setParameter("packageId", qa.getPackages().getPackageId())
				.setParameter("userId", qa.getUser().getUserId())
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (QuestionAssign)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<QuestionAssign> findByUser(String userId) {
		List<QuestionAssign> list = super.entityManager
				.createQuery("From QuestionAssign where"
						+ " user_id =: userId")
				.setParameter("userId", userId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<QuestionAssign> sendQuestion(String userId) {
		List<QuestionAssign> list = super.entityManager
				.createQuery("From QuestionAssign where"
						+ " user_id =: userId and"
						+ " flag = false")
				.setParameter("userId", userId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return list;
	}
	
	@Transactional
	public BigInteger countQuestion(String userId) {
		Query query  = super.entityManager
				.createNativeQuery("select count(pd.question_id) from group2.tbl_tr_question_assign qa "
						+ "join group2.tbl_m_package p on qa.package_id = p.package_id "
						+ "join group2.tbl_m_package_detail pd on p.package_id = pd.package_id "
						+ "join group2.tbl_m_question q on pd.question_id = q.question_id "
						+ "WHERE qa.user_id = '" + userId + "'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}

}
