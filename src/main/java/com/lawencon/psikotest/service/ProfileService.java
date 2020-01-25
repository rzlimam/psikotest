package com.lawencon.psikotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.ProfileDao;
import com.lawencon.psikotest.entity.Profile;

@Service("profileService")
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	
	public Profile findById(String id) {
		Profile profile = profileDao.findById(id);
		return profile;
	}
	
	public Profile findByBk(String email, String phone) {
		Profile myProfile = profileDao.findByBk(email, phone);
		return myProfile;
	}
	
	public List<Profile> findByName(String search) {
		List<Profile> myProfile = profileDao.findByName(search);
		return myProfile;
	}
	
	public List<Profile> findByEmail(String search) {
		List<Profile> myProfile = profileDao.findByEmail(search);
		return myProfile;
	}
	
	public List<Profile> findByPhone(String search) {
		List<Profile> myProfile = profileDao.findByPhone(search);
		return myProfile;
	}
	
	public Profile insert(Profile profile) throws Exception {
		try {
			valIdNull(profile);
//			valBkNotNull(profile.getEmail(), profile.getPhone());
//			valBkNotExist(profile.getEmail(), profile.getPhone());
//			ValNonBk(profile);
			return profileDao.save(profile);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void update(Profile profile) throws Exception {
		try {
			valIdNotNull(profile);
//			ValIdExist(profile.getProfileId());
//			valBkNotNull(profile.getEmail(), profile.getPhone());
//			ValNonBk(profile);
			profileDao.save(profile);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			profileDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Profile> getAll(){
		List<Profile> profile = profileDao.getAll();
		return profile;
	}
	
	//Validasi
	
			//Id null
			private Exception valIdNull(Profile profile) throws Exception{
				if(profile.getProfileId()!=null) {
					throw new Exception("Profile Id not null");
				}
				return null;
			}
			
			//Id null
			private Exception valIdNotNull(Profile profile) throws Exception{
				if(profile.getProfileId()==null || profile.getProfileId().trim().equals("")) {
					throw new Exception("Profile Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(String email, String phone) throws Exception{
				if(email == null || email.trim().equals("")) {
					throw new Exception("Email is null");
				} else if(phone.trim().equals("")) {
					throw new Exception("Phone is null");
				}
				return null;
			}
			
			//BK not exist
			private Exception valBkNotExist(String email, String phone) throws Exception{
				if(findByBk(email, phone)!=null) {
					throw new Exception("BK Profile is Exist");
				}
				return null;
			}
			
			//NonBk not null
			private Exception ValNonBk(Profile profile) throws Exception {
				if(profile.getProfileName()==null || profile.getProfileName().trim().equals("")) {
					throw new Exception("Profile is empty");
				} else if(profile.getDateOfBirth()==null) {
					throw new Exception("Date of birth is empty");
				} else if(profile.getAddress()==null || profile.getAddress().trim().equals("")) {
					throw new Exception("Address is empty");
				} else if(profile.getGender()==null || profile.getGender().trim().equals("")) {
					throw new Exception("Gender is empty");
				} else if(profile.getIsActive()==null) {
					throw new Exception("Active state is empty");
				}
				return null;
			}
			
			//Bk not change
			private Exception ValBkNotChange(Profile profile) throws Exception {
				if(!(findById(profile.getProfileId()).getEmail().equalsIgnoreCase(profile.getProfileName())
						&& findById(profile.getProfileId()).getPhone().equalsIgnoreCase(profile.getPhone()))) {
					throw new Exception("BK Profile is Change");
				}
				return null;
			}
			
			//Id Exist
			private Exception ValIdExist(String id) throws Exception {
				if(findById(id)==null) {
					throw new Exception("Id Profile is not Exist");
				}
				return null;
			}

}
