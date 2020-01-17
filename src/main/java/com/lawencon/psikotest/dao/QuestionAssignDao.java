package com.lawencon.psikotest.dao;

import java.util.List;

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
				.setParameter("packageId", qa.getPackagee().getPackageId())
				.setParameter("userId", qa.getUser().getUserId())
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (QuestionAssign)list.get(0);
	}

}
