package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.entity.POJOStats1;
import com.lawencon.psikotest.entity.POJOStats2;
import com.lawencon.psikotest.entity.PackageDetail;
import com.lawencon.psikotest.entity.Packages;

@Repository("packageDetailDao")
public class PackageDetailDao extends EntityDao {
	
	@Autowired
	private PackageDao packDao;
	
	@Autowired
	private QuestionDao qDao;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PackageDetail> getAll() {
		List<PackageDetail> list = super.entityManager
				.createQuery("From PackageDetail where isActive = true")
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PackageDetail> findByPackage(String packageId) {
		List<PackageDetail> list = super.entityManager
				.createQuery("From PackageDetail WHERE "
						+ "packages.packageId=:packageId and isActive = true")
				.setParameter("packageId", packageId)
				.getResultList();
		return list;
	}
	
	@Transactional
	public BigInteger countQuestion(String packageId) {
		Query query  = super.entityManager
				.createNativeQuery("Select count(*) FROM group2.tbl_m_package_detail"
						+ " WHERE package_id = '" + packageId + "'");
		BigInteger count =  (BigInteger) query.getSingleResult(); 
		return count;
	}
	

}
