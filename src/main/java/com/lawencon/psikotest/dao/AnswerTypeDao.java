package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.AnswerType;

@Repository("answerTypeDao")
public class AnswerTypeDao extends EntityDao {
	
	@Transactional
	public void save(AnswerType at) {
		super.entityManager.merge(at);
	}
	
	@Transactional
	public void delete(String id) {
		AnswerType at = findById(id);
		super.entityManager.remove(at);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public AnswerType findById(String id) {
		List<AnswerType> list = super.entityManager
				.createQuery("From AnswerType where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return new AnswerType();
		else 
			return (AnswerType)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public AnswerType findByCode(String code) {
		List<AnswerType> list = super.entityManager
				.createQuery("From AnswerType where codeAnswerType=:code")
				.setParameter("code", code)
				.getResultList();
		if(list.size() == 0)
			return new AnswerType();
		else 
			return (AnswerType)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<AnswerType> getAll() {
		List<AnswerType> list = super.entityManager
				.createQuery("From AnswerType")
				.getResultList();
		return list;
	}

}
