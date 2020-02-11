package com.lawencon.psikotest.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.POJODashboard;
import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.entity.Packages;

@Repository
public class DashboardDao extends EntityDao {
	
	@Transactional
	public BigInteger countPass() {
		Query query  = super.entityManager
				.createNativeQuery("select count(status) from group2.tbl_header_applicant_answer thaa"
						+ " where thaa.status = 'Lulus'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countFail() {
		Query query  = super.entityManager
				.createNativeQuery("select count(status) from group2.tbl_header_applicant_answer thaa"
						+ " where thaa.status <> 'Lulus'");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countQuestion() {
		Query query  = super.entityManager
				.createNativeQuery("select count(*) from group2.tbl_m_question");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@Transactional
	public BigInteger countPackage() {
		Query query  = super.entityManager
				.createNativeQuery("select count(*) from group2.tbl_m_package");
		BigInteger count = (BigInteger) query.getSingleResult(); 
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<POJODashboard> recentTest() {
		List<POJODashboard> stats = new ArrayList<POJODashboard>();
			Query queryProfileName  = super.entityManager
					.createNativeQuery("select pr.profile_name"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_tr_question_assign qa on tmu.user_id = qa.user_id"
							+ " join group2.tbl_m_package p on qa.package_id = p.package_id" 
							+ " join group2.tbl_header_applicant_answer th on tmu.user_id = th.user_id"
							+ " join group2.tbl_m_profile pr on tmu.profile_id = pr.profile_id"
							+ " group by pr.email"
							+ " order by th.date_of_answer desc"
							+ " limit 10");
			List<String> profileName = queryProfileName.getResultList();
			
			Query queryDate  = super.entityManager
					.createNativeQuery("select th.date_of_answer"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_tr_question_assign qa on tmu.user_id = qa.user_id"
							+ " join group2.tbl_m_package p on qa.package_id = p.package_id" 
							+ " join group2.tbl_header_applicant_answer th on tmu.user_id = th.user_id"
							+ " join group2.tbl_m_profile pr on tmu.profile_id = pr.profile_id"
							+ " group by pr.email"
							+ " order by th.date_of_answer desc"
							+ " limit 10");
			List<Date> date = queryDate.getResultList();
			
			Query queryStatus = super.entityManager
					.createNativeQuery("select th.status"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_tr_question_assign qa on tmu.user_id = qa.user_id"
							+ " join group2.tbl_m_package p on qa.package_id = p.package_id" 
							+ " join group2.tbl_header_applicant_answer th on tmu.user_id = th.user_id"
							+ " join group2.tbl_m_profile pr on tmu.profile_id = pr.profile_id"
							+ " group by pr.email"
							+ " order by th.date_of_answer desc"
							+ " limit 10");
			List<String> status = queryStatus.getResultList();
			
			Query queryPosition  = super.entityManager
					.createNativeQuery("select pr.applied_position"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_tr_question_assign qa on tmu.user_id = qa.user_id"
							+ " join group2.tbl_m_package p on qa.package_id = p.package_id" 
							+ " join group2.tbl_header_applicant_answer th on tmu.user_id = th.user_id"
							+ " join group2.tbl_m_profile pr on tmu.profile_id = pr.profile_id"
							+ " group by pr.email"
							+ " order by th.date_of_answer desc"
							+ " limit 10");
			List<String> position = queryPosition.getResultList();
			
			for(int i=0; i<profileName.size(); i++) {
				POJODashboard dash = new POJODashboard();
				dash.setProfileName(profileName.get(i));
				dash.setDateOfAnswer(date.get(i));
				dash.setStatus(status.get(i));
				dash.setAppliedPosition(position.get(i));
				stats.add(dash);
			}
		
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	public List<POJODashboard> ranking() {
		List<POJODashboard> stats = new ArrayList<POJODashboard>();
			Query queryProfileName  = super.entityManager
					.createNativeQuery("select pro.profile_name"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_header_applicant_answer thaa on tmu.user_id = thaa.user_id"
							+ " join group2.tbl_m_profile pro on tmu.profile_id = pro.profile_id"
							+ " order by thaa.score desc"
							+ " limit 10");
			List<String> profileName = queryProfileName.getResultList();
			
			Query queryPoint  = super.entityManager
					.createNativeQuery("select thaa.score"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_header_applicant_answer thaa on tmu.user_id = thaa.user_id"
							+ " join group2.tbl_m_profile pro on tmu.profile_id = pro.profile_id"
							+ " order by thaa.score desc"
							+ " limit 10");
			List<BigDecimal> score = queryPoint.getResultList();
			
			Query queryPosition = super.entityManager
					.createNativeQuery("select pro.applied_position"  
							+ " from group2.tbl_m_user tmu"  
							+ " join group2.tbl_header_applicant_answer thaa on tmu.user_id = thaa.user_id"
							+ " join group2.tbl_m_profile pro on tmu.profile_id = pro.profile_id"
							+ " order by thaa.score desc"
							+ " limit 10");
			List<String> position = queryPosition.getResultList();			
			
			for(int i=0; i<profileName.size(); i++) {
				POJODashboard dash = new POJODashboard();
				dash.setProfileName(profileName.get(i));
				dash.setScore(score.get(i).doubleValue());
				dash.setAppliedPosition(position.get(i));
				stats.add(dash);
			}
		
		return stats;
	}

}
