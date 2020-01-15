package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.Question;

@Repository("questionDao")
public class QuestionDao extends EntityDao {
	
	@Transactional
	public void save(Question qs) {
		super.entityManager.merge(qs);
	}
	
	@Transactional
	public void delete(String id) {
		Question qs = findById(id);
		super.entityManager.remove(qs);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Question findById(String id) {
		List<Question> list = super.entityManager
				.createQuery("From Question where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return new Question();
		else 
			return (Question)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Question> getAll() {
		List<Question> list = super.entityManager
				.createQuery("From Question")
				.getResultList();
		return list;
	}

}
