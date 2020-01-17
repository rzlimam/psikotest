package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.PointForEachType;

@Repository("pointTypeDao")
public class PointTypeDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PointForEachType> getAll() {
		List<PointForEachType> list = super.entityManager
				.createQuery("From PointForEachType")
				.getResultList();
		return list;
	}
	
	@Transactional
	public PointForEachType save(PointForEachType point) {
		return super.entityManager.merge(point);
	}
	
	@Transactional
	public void delete(String id) {
		PointForEachType point = findById(id);
		super.entityManager.remove(point);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public PointForEachType findById(String id) {
		List<PointForEachType> list = super.entityManager
				.createQuery("From PointForEachType where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (PointForEachType)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public PointForEachType findByBk(String questionTypeId, String userId) {
		List<PointForEachType> list = super.entityManager
				.createQuery("From PointForEachType where question_type_id=:qtid "
						+ "and user_id=:userId")
				.setParameter("qtid", questionTypeId)
				.setParameter("userId", userId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (PointForEachType)list.get(0);
	}

}
