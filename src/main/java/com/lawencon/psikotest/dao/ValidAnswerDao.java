package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.ValidAnswer;

@Repository("validAnswerDao")
public class ValidAnswerDao extends EntityDao {
	
	@Transactional
	public void save(ValidAnswer va) {
		super.entityManager.merge(va);
	}
	
	@Transactional
	public void delete(String id) {
		ValidAnswer va = findById(id);
		super.entityManager.remove(va);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ValidAnswer findById(String id) {
		List<ValidAnswer> list = super.entityManager
				.createQuery("From ValidAnswer where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return new ValidAnswer();
		else 
			return (ValidAnswer)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ValidAnswer> getAll() {
		List<ValidAnswer> list = super.entityManager
				.createQuery("From ValidAnswer")
				.getResultList();
		return list;
	}

}
