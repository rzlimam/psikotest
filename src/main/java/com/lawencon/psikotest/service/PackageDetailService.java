package com.lawencon.psikotest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.PackageDetailDao;
import com.lawencon.psikotest.entity.POJOPackage;
import com.lawencon.psikotest.entity.PackageDetail;

@Service("pdService")
public class PackageDetailService {
	
	@Autowired
	private PackageDetailDao pdDao;
	
	@Autowired
	private PackageService packService;
	
	@Autowired
	private QuestionService questionService;
	
	public List<PackageDetail> getAll(){
		List<PackageDetail> list = pdDao.getAll();
		return list;
	}
	
	public PackageDetail findById(String id) {
		PackageDetail pd = pdDao.findById(id);
		return pd;
	}
	
	public PackageDetail findByBk(String packageId, String questionId) {
		PackageDetail pd = pdDao.findByBk(packageId, questionId);
		return pd;
	}
	
	public List<PackageDetail> findByPackage(String id){
		List<PackageDetail> list = pdDao.findByPackage(id);
		return list;
	}
	
	public Integer countQuestion(String questionId) {
		Integer totalQuestion = pdDao.countQuestion(questionId).intValue();
		return totalQuestion;
	}
	
	public List<PackageDetail> getPackage(){
		List<PackageDetail> list = pdDao.getAll();
//		List<POJOPackage> packages = new ArrayList<POJOPackage>();
//		for (PackageDetail pack : list) {
//			POJOPackage p = new POJOPackage();
//			p.setPackageName(pack.getPackages().getPackageName());
//			p.setAmountOfTime(pack.getPackages().getAmountOfTime().toString());
//			p.setDescription(pack.getPackages().getDescription());
//			p.setTotalQuestion(countQuestion(pack.getPackages().getPackageId()));
//			packages.add(p);
//		}
		return list;
	}
	
	public void insert(PackageDetail pd) throws Exception {
		try {
			valIdNull(pd);
			valBkNotNull(pd);
//			valBkNotExist(pd);
//			ValPackageExist(pd.getPackages().getPackageId());
//			ValQuestionExist(pd.getQuestion().getQuestionId());
			pdDao.save(pd);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(PackageDetail pd) throws Exception {
		try {
			valIdNotNull(pd);
			ValIdExist(pd.getPackageQuestionId());
//			valBkNotNull(pd);
//			ValBkNotChange(pd);
			pdDao.save(pd);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			pdDao.delete(id);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	//Validasi
	
			//Id null
			private Exception valIdNull(PackageDetail pd) throws Exception{
				if(pd.getPackageQuestionId()!=null) {
					throw new Exception("PackageDetail Id not null");
				}
				return null;
			}
			
			//Id null
			private Exception valIdNotNull(PackageDetail pd) throws Exception{
				if(pd.getPackageQuestionId()==null || pd.getPackageQuestionId().trim().equals("")) {
					throw new Exception("PackageDetail Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(PackageDetail pd) throws Exception{
				if(pd.getPackages()==null || pd.getQuestion()==null) {
					throw new Exception("PackageDetail BK is null");
				}
				return null;
			}
			
			//BK not exist
			private Exception valBkNotExist(PackageDetail pd) throws Exception{
				if(findByBk(pd.getPackages().getPackageId(), pd.getQuestion().getQuestionId())!=null) {
					throw new Exception("PackageDetail BK is Exist");
				}
				return null;
			}
			
			
			//Bk not change
			private Exception ValBkNotChange(PackageDetail pd) throws Exception {
				if(!(findById(pd.getPackageQuestionId()).getPackages().getPackageId()
						.equalsIgnoreCase(pd.getPackages().getPackageId()) ||
						findById(pd.getPackageQuestionId()).getQuestion().getQuestionId()
						.equalsIgnoreCase(pd.getQuestion().getQuestionId()))) {
					throw new Exception("BK is Change");
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
			
			//Package Exist
			private Exception ValPackageExist(String id) throws Exception {
				if(packService.findById(id)==null) {
					throw new Exception("Package is not Exist");
				}
				return null;
			}
			
			//Question Exist
			private Exception ValQuestionExist(String id) throws Exception {
				if(questionService.findById(id)==null) {
					throw new Exception("Question is not Exist");
				}
				return null;
			}

}
