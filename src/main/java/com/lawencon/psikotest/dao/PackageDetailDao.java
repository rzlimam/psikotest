package com.lawencon.psikotest.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.entity.PackageDetail;
import com.lawencon.psikotest.entity.Packages;

@Repository("packageDetailDao")
public class PackageDetailDao extends EntityDao {
	
	@Autowired
	private PackageDao packDao;
	
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
	public BigInteger countQuestion(String packageId) {
		Query query  = super.entityManager
				.createNativeQuery("Select count(*) FROM group2.tbl_m_package_detail"
						+ " WHERE package_id = '" + packageId + "'");
		BigInteger count =  (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats> questionPerPackage() {
		List<Packages> packages = packDao.getAll();
		List<POJOStats> correctStats = new ArrayList<POJOStats>();
		for (Packages p: packages) {
//			POJOStats cs = new POJOStats();
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select q.question_title from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'"  
							+ " and daa.point = 1" 
							+ " group by q.question_title" 
							+ " limit 3");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point) from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'"  
							+ " and daa.point = 1" 
							+ " group by q.question_title"
							+ " order by count(daa.point) asc" 
							+ " limit 3");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats cs = new POJOStats();
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				correctStats.add(cs);
			}
			
		}
		return correctStats;
	}

}
