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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PackageDetail> findByPackage(String packageId) {
		List<PackageDetail> list = super.entityManager
				.createQuery("From PackageDetail WHERE "
						+ "packages.packageId=:packageId")
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats> correctPerPackage() {
		List<Packages> packages = packDao.getAll();
		List<POJOStats> stats = new ArrayList<POJOStats>();
		for (Packages p: packages) {
//			POJOStats cs = new POJOStats();
			Query queryPackage  = super.entityManager
					.createNativeQuery("select p.package_name from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'"  
							+ " and daa.point <> 0"
							+ " limit 5");
			List<String> pack = queryPackage.getResultList();
			
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select q.question_title from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'"  
							+ " and daa.point <> 0" 
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 5");
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
							+ " and daa.point <> 0" 
							+ " group by p.package_name,q.question_title "
							+ " order by count(daa.point) desc" 
							+ " limit 5");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats cs = new POJOStats();
				cs.setPackageName(pack.get(i));
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				stats.add(cs);
			}
			
		}
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats> falsePerPackage() {
		List<Packages> packages = packDao.getAll();
		List<POJOStats> stats = new ArrayList<POJOStats>();
		for (Packages p: packages) {
//			POJOStats cs = new POJOStats();
			Query queryPackage  = super.entityManager
					.createNativeQuery("select p.package_name from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'"  
							+ " and daa.point = 0"
							+ " limit 5");
			List<String> pack = queryPackage.getResultList();
			
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select q.question_title from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'"  
							+ " and daa.point = 0" 
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 5");
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
							+ " and daa.point = 0" 
							+ " group by p.package_name,q.question_title "
							+ " order by count(daa.point) desc" 
							+ " limit 5");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats cs = new POJOStats();
				cs.setPackageName(pack.get(i));
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				stats.add(cs);
			}
			
		}
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats1> mostCorrect() {
		List<POJOStats1> stats = new ArrayList<POJOStats1>();
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select q.question_title"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_question q on pd.question_id = q.question_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point <> 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point)"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_question q on pd.question_id = q.question_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point <> 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats1 cs = new POJOStats1();
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				stats.add(cs);
			}
		
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats1> mostFalse() {
		List<POJOStats1> stats = new ArrayList<POJOStats1>();
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select q.question_title"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_question q on pd.question_id = q.question_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point = 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point)"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_question q on pd.question_id = q.question_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point = 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats1 cs = new POJOStats1();
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				stats.add(cs);
			}
		
		return stats;
	}

}
