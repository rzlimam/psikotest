package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.Question;
import com.lawencon.psikotest.entity.SearchQuestion;

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
				.createQuery("From Question WHERE isActive = :isActive"
						+ " ORDER BY dateOfQuestion DESC")
				.setParameter("isActive", true)
				.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Question> getQuestion(String qtId) {
		List<Question> list = super.entityManager
				.createQuery("From Question WHERE "
						+ "questionType.questionTypeId=:qtId")
				.setParameter("qtId", qtId)
				.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Question findByTitle(String search) {
		List<Question> list = super.entityManager
				.createQuery("From Question where lower(questionTitle) like "
						+ "concat('%', :search, '%')")
				.setParameter("search", search.toLowerCase())
				.getResultList();
		if(list.size() == 0)
			return new Question();
		else 
			return (Question)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Question> findQuestion(SearchQuestion sq) {
		StringBuilder sb = new StringBuilder();
		sb.append("From Question where 1=1 ");
		if(sq.getQuestionType()!=null) {
			sb.append("and lower(questionType.questionTypeTitle) like :type ");
		} else if(sq.getQuestionTitle()!=null) {
			sb.append("and lower(questionTitle) like :title ");
		}
		
		Query query = super.entityManager.createQuery(sb.toString());
		
		if(sq.getQuestionType()!=null) {
			query.setParameter("type", "%" + sq.getQuestionType().toLowerCase() + "%");
		} else if(sq.getQuestionTitle()!=null) {
			query.setParameter("title", "%" + sq.getQuestionTitle().toLowerCase() + "%");
		}
		
		List<Question> list = query.getResultList();
		
		if(list.size() == 0)
			return null;
		else 
			return list;
	}
	
	@Transactional
	public BigInteger countQuestion() {
		Query query  = super.entityManager
				.createNativeQuery("Select count(*) FROM group2.tbl_m_question");
		BigInteger count =  (BigInteger) query.getSingleResult(); 
		return count;
	}

}
