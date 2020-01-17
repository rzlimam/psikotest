package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.DetailApplicantAnswerDao;
import com.lawencon.psikotest.entity.DetailApplicantAnswer;

@Service("daaService")
public class DetailApplicantAnswerService {
	
	@Autowired
	private DetailApplicantAnswerDao detailaaDao;
	
	@Autowired
	private HeaderApplicantAnswerService haaService;
	
	@Autowired
	private PackageDetailService pdService;
	
	public List<DetailApplicantAnswer> getAll(){
		List<DetailApplicantAnswer> list = detailaaDao.getAll();
		return list;
	}
	
	public DetailApplicantAnswer findById(String id) {
		DetailApplicantAnswer daa = detailaaDao.findById(id);
		return daa;
	}
	
	public DetailApplicantAnswer findByBk(String aaId, String pqId) {
		DetailApplicantAnswer daa = detailaaDao.findByBk(aaId, pqId);
		return daa;
	}
	
	public void insert(DetailApplicantAnswer daa) throws Exception {
		try {
			valIdNull(daa);
			valBkNotNull(daa);
			valBkNotExist(daa);
			ValHAAExist(daa.getApplicantAnswerId().getApplicantAnswerId());
			ValPDExist(daa.getPackageQuestion().getPackageQuestionId());
//			ValNonBk(daa);
			detailaaDao.save(daa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(DetailApplicantAnswer daa) throws Exception {
		try {
			valIdNotNull(daa);
			ValIdExist(daa.getDetailAnswerId());
			valBkNotNull(daa);
			ValBkNotChange(daa);
//			ValNonBk(daa);
			detailaaDao.save(daa);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			detailaaDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
		//Id null
		private Exception valIdNull(DetailApplicantAnswer daa) throws Exception{
			if(daa.getDetailAnswerId()!=null) {
				throw new Exception("Detail Applicant Answer Id not null");
			}
			return null;
		}
		
		//Id not null
		private Exception valIdNotNull(DetailApplicantAnswer daa) throws Exception{
			if(daa.getDetailAnswerId()==null || daa.getDetailAnswerId().trim().equals("")) {
				throw new Exception("Detail Applicant Answer Id null");
			}
			return null;
		}
		
		//BK not null
		private Exception valBkNotNull(DetailApplicantAnswer daa) throws Exception{
			if(daa.getApplicantAnswerId().getApplicantAnswerId()==null || daa.getPackageQuestion().getPackageQuestionId() == null) {
				throw new Exception("Detail Applicant Answer BK is null");
			}
			return null;
		}
		
		//BK not exist
		private Exception valBkNotExist(DetailApplicantAnswer daa) throws Exception{
			if(findByBk(daa.getApplicantAnswerId().getApplicantAnswerId(), daa.getPackageQuestion().getPackageQuestionId())!=null) {
				throw new Exception("Detail Applicant Answer BK is Exist");
			}
			return null;
		}
		
		//NonBk not null
		private Exception ValNonBk(DetailApplicantAnswer daa) throws Exception {
			if(daa.getApplicatnAnswer()==null) {
				throw new Exception("There is empty field in Detail Applicant Answer");
			}
			return null;
		}
		
		//Bk not change
		private Exception ValBkNotChange(DetailApplicantAnswer daa) throws Exception {
			if(!(findById(daa.getDetailAnswerId()).getApplicantAnswerId().getApplicantAnswerId()
					.equalsIgnoreCase(daa.getApplicantAnswerId().getApplicantAnswerId()) ||
					findById(daa.getDetailAnswerId()).getPackageQuestion().getPackageQuestionId()
					.equalsIgnoreCase(daa.getPackageQuestion().getPackageQuestionId()))) {
				throw new Exception("Detail Applicant Answer BK is Change");
			}
			return null;
		}
		
		//Id Exist
		private Exception ValIdExist(String id) throws Exception {
			if(findById(id)==null) {
				throw new Exception("Id is not Exist");
			}
			return null;
		}
		
		//Hedaer Applicant Answer Exist
		private Exception ValHAAExist(String id) throws Exception {
			if(haaService.findById(id)==null) {
				throw new Exception("Header Applicant Answer is not Exist");
			}
			return null;
		}
		
		//Package Question Exist
		private Exception ValPDExist(String id) throws Exception {
			if(pdService.findById(id)==null) {
				throw new Exception("Package Detail is not Exist");
			}
			return null;
		}

}
