package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.QuestionType;

@Repository("QuestionTypeDao")
public class QuestionTypeDao extends EntityDao {
	
	@Transactional
	public void save(QuestionType qt) {
		super.entityManager.merge(qt);
	}
	
	@Transactional
	public void delete(String id) {
		QuestionType qt = findById(id);
		super.entityManager.remove(qt);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public QuestionType findById(String id) {
		List<QuestionType> list = super.entityManager
				.createQuery("From QuestionType where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (QuestionType)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<QuestionType> getAll() {
		List<QuestionType> list = super.entityManager
				.createQuery("From QuestionType")
				.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public QuestionType findByBk(String qtTitle) {
		List<QuestionType> list = super.entityManager
				.createQuery("From QuestionType where question_type_title=:qtTitle")
				.setParameter("qtTitle", qtTitle)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (QuestionType)list.get(0);
	}

}
