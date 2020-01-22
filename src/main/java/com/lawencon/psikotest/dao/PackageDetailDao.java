package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.PackageDetail;

@Repository("packageDetailDao")
public class PackageDetailDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PackageDetail> getAll() {
		List<PackageDetail> list = super.entityManager
				.createQuery("From PackageDetail")
				.getResultList();
		return list;
	}
	
	@Transactional
	public PackageDetail save(PackageDetail pack) {
		return super.entityManager.merge(pack);
		
	}
	
	@Transactional
	public void delete(String id) {
		PackageDetail pack = findById(id);
		super.entityManager.remove(pack);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public PackageDetail findById(String id) {
		List<PackageDetail> list = super.entityManager
				.createQuery("From PackageDetail where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (PackageDetail)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public PackageDetail findByBk(String packageId, String questionId) {
		List<PackageDetail> list = super.entityManager
				.createQuery("From PackageDetail where package_id=:packageId"
						+ " and question_id=:questionId")
				.setParameter("packageId", packageId)
				.setParameter("questionId", questionId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (PackageDetail)list.get(0);
	}
	
	@Transactional
	public Integer countQuestion(String questionId) {
		Query query  = super.entityManager
				.createNativeQuery("Select count(*) FROM tbl_m_package_detail"
						+ " WHERE question_id = '" + questionId + "'");
		Integer count =  (Integer) query.getSingleResult(); 
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PackageDetail> getPackage() {
		List<PackageDetail> list = super.entityManager
				.createQuery("SELECT DISTINCT PD.packages "
						+ "FROM PackageDetail PD")
				.getResultList();
		if(list.size() == 0)
			return null;
		else
			return list;
	}
	

}
