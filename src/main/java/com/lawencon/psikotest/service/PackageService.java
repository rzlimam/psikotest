package com.lawencon.psikotest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.PackageDao;
import com.lawencon.psikotest.dao.PackageDetailDao;
import com.lawencon.psikotest.dao.QuestionTypeDao;
import com.lawencon.psikotest.entity.POJOPackage;
import com.lawencon.psikotest.entity.Packages;

@Service("packService")
public class PackageService {
	
	@Autowired
	private PackageDao packDao;
	
	@Autowired
	private PackageDetailDao pdDao;
	
	@Autowired
	private QuestionTypeDao qtDao;
	
	public List<Packages> getAll(){
		List<Packages> list = packDao.getAll();
		return list;
	}
	
	public Packages findById(String id) {
		Packages pack = packDao.findById(id);
		return pack;
	}
	
	public Packages findByBk(String packageName) {
		Packages pack = packDao.findByBk(packageName);
		return pack;
	}
	
	public List<Packages> findByQT(String qt) {
		List<Packages> pack = packDao.findByQT(qt);
		return pack;
	}
	
	public List<POJOPackage> getPackage(){
		List<Packages> list = packDao.getAll();
		List<POJOPackage> packages = new ArrayList<POJOPackage>();
		for (Packages pack : list) {
			POJOPackage p = new POJOPackage();
			p.setPackageId(pack.getPackageId());
			p.setPackageName(pack.getPackageName());
			p.setQuestionType(pack.getQuestionType().getQuestionTypeTitle());
			p.setAmountOfTime(pack.getAmountOfTime().toString());
			p.setDescription(pack.getDescription());
			if(pack.getPackageDetails().size() != 0) {
				p.setTotalQuestion(pdDao.countQuestion(pack.getPackageDetails().get(0).getPackages().getPackageId()).intValue());
			} else {
				p.setTotalQuestion(0);
			}
			
			packages.add(p);
		}
		return packages;
	}
	
	public void insert(Packages pack) throws Exception {
		try {
			valIdNull(pack);
			valBkNotNull(pack);
			valBkNotExist(pack);
			QTExist(pack.getQuestionType().getQuestionTypeId());
			ValNonBk(pack);
			packDao.save(pack);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void update(Packages pack) throws Exception {
		try {
			valIdNotNull(pack);
			ValIdExist(pack.getPackageId());
			valBkNotNull(pack);
			ValBkNotChange(pack);
			QTExist(pack.getQuestionType().getQuestionTypeId());
			ValNonBk(pack);
			packDao.save(pack);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			Packages pack = findById(id);
			pack.setIsActive(false);
			packDao.save(pack);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	
	//Validasi
	
			//Id null
			private Exception valIdNull(Packages pack) throws Exception{
				if(pack.getPackageId()!=null) {
					throw new Exception("Package Id not null");
				}
				return null;
			}
			
			//Id null
			private Exception valIdNotNull(Packages pack) throws Exception{
				if(pack.getPackageId()==null || pack.getPackageId().trim().equals("")) {
					throw new Exception("Package Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(Packages pack) throws Exception{
				if(pack.getPackageName()==null) {
					throw new Exception("Package Name is null");
				}
				return null;
			}
			
			//BK not exist
			private Exception valBkNotExist(Packages pack) throws Exception{
				if(findByBk(pack.getPackageName())!=null) {
					throw new Exception("Package Name is Exist");
				}
				return null;
			}
			
			//NonBk not null
			private Exception ValNonBk(Packages pack) throws Exception {
				if(pack.getAmountOfTime()==null) {
					throw new Exception("Amount of time is empty");
				} else if(pack.getDescription()==null || pack.getDescription().trim().equals("")) {
					throw new Exception("Description is empty");
				} else if(pack.getIsActive()==null) {
					throw new Exception("Active state is empty");
				}
				return null;
			}
			
			//Bk not change
			private Exception ValBkNotChange(Packages pack) throws Exception {
				if(!findById(pack.getPackageId()).getPackageName().equalsIgnoreCase(pack.getPackageName())) {
					throw new Exception("Package BK is Change");
				}
				return null;
			}
			
			//Id Exist
			private void ValIdExist(String id) throws Exception {
				if(findById(id)==null) {
					throw new Exception("Id is not Exist");
				}
			}
			
			//Question Type Exist
			private void QTExist(String id) throws Exception {
				if(qtDao.findById(id)==null) {
					throw new Exception("Question Type is not Exist");
				}
			}

}
