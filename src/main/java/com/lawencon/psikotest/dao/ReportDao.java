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
import com.lawencon.psikotest.entity.Packages;

@Repository
@Transactional
public class ReportDao extends EntityDao {
	
	@Autowired
	private PackageDao packDao;
	
	@SuppressWarnings("unchecked")
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
			
			Query queryTotal  = super.entityManager
					.createNativeQuery("select count(q.question_title) from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'" 
							+ " group by q.question_title");
			List<BigInteger> totalQuestion = queryTotal.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats cs = new POJOStats();
				cs.setPackageName(pack.get(i));
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				cs.setTotalQuestion(totalQuestion.get(i).intValue());
				Double percentage = (point.get(i).doubleValue()/totalQuestion.get(i).doubleValue()) * 100;
				cs.setPercentage(percentage);
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
			
			Query queryTotal  = super.entityManager
					.createNativeQuery("select count(q.question_title) from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id" 
							+ " where p.package_id = '" + p.getPackageId() + "'" 
							+ " group by q.question_title");
			List<BigInteger> totalQuestion = queryTotal.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats cs = new POJOStats();
				cs.setPackageName(pack.get(i));
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				cs.setTotalQuestion(totalQuestion.get(i).intValue());
				Double percentage = (point.get(i).doubleValue()/totalQuestion.get(i).doubleValue()) * 100;
				cs.setPercentage(percentage);
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
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id"
							+ " where daa.point <> 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point)"
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id"
							+ " where daa.point <> 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<BigInteger> point = queryPoint.getResultList();
			
			Query queryTotal  = super.entityManager
					.createNativeQuery("select count(q.question_title) from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id"  
							+ " group by q.question_title"
							+ " order by count(q.question_title) desc");
			List<BigInteger> totalQuestion = queryTotal.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats1 cs = new POJOStats1();
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				cs.setTotalQuestion(totalQuestion.get(i).intValue());
				Double percentage = (point.get(i).doubleValue()/totalQuestion.get(i).doubleValue()) * 100;
				cs.setPercentage(percentage);
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
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id"
							+ " where daa.point = 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point)"
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id"
							+ " where daa.point = 0"
							+ " group by q.question_title"
							+ " order by count(daa.point) desc"
							+ " limit 10");
			List<BigInteger> point = queryPoint.getResultList();
			
			Query queryTotal  = super.entityManager
					.createNativeQuery("select count(q.question_title) from"  
							+ " group2.tbl_detail_applicant_answer daa"  
							+ " join group2.tbl_m_package_detail pd on"
							+ " daa.package_question_id = pd.package_question_id" 
							+ " join group2.tbl_m_package p on pd.package_id = p.package_id"
							+ " join group2.tbl_m_question q on pd.question_id = "
							+ " q.question_id"  
							+ " group by q.question_title"
							+ " order by count(q.question_title) desc");
			List<BigInteger> totalQuestion = queryTotal.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats1 cs = new POJOStats1();
				cs.setQuestion(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				cs.setTotalQuestion(totalQuestion.get(i).intValue());
				Double percentage = (point.get(i).doubleValue()/totalQuestion.get(i).doubleValue()) * 100;
				cs.setPercentage(percentage);
				stats.add(cs);
			}
		
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats2> easiestPackage() {
		List<POJOStats2> stats = new ArrayList<POJOStats2>();
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select p.package_name"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_package p  on p.package_id = pd.package_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point <> 0"
							+ " group by p.package_name"
							+ " order by count(daa.point) desc");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point)"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_package p  on p.package_id = pd.package_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point <> 0"
							+ " group by p.package_name"
							+ " order by count(daa.point) desc");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats2 cs = new POJOStats2();
				cs.setPackageName(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				stats.add(cs);
			}
		
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<POJOStats2> hardestPackage() {
		List<POJOStats2> stats = new ArrayList<POJOStats2>();
			Query queryQuestion  = super.entityManager
					.createNativeQuery("select p.package_name"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_package p  on p.package_id = pd.package_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point = 0"
							+ " group by p.package_name"
							+ " order by count(daa.point) desc");
			List<String> question = queryQuestion.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select count(daa.point)"
							+ " from group2.tbl_m_package_detail pd" 
							+ " join group2.tbl_m_package p  on p.package_id = pd.package_id"
							+ " join group2.tbl_detail_applicant_answer daa on pd.package_question_id = daa.package_question_id"
							+ " where daa.point = 0"
							+ " group by p.package_name"
							+ " order by count(daa.point) desc");
			List<BigInteger> point = queryPoint.getResultList();
			
			for(int i=0; i<question.size(); i++) {
				POJOStats2 cs = new POJOStats2();
				cs.setPackageName(question.get(i));
				cs.setCorrect(point.get(i).intValue());
				stats.add(cs);
			}
		
		return stats;
	}

}
