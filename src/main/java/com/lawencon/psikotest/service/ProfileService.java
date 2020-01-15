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
	
	public Profile insert(Profile profile) throws Exception {
		try {
			valIdNull(profile);
			valBkNotNull(profile.getEmail(), profile.getPhone());
			valBkNotExist(profile.getEmail(), profile.getPhone());
			ValNonBk(profile);
			return profileDao.save(profile);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void update(Profile profile) throws Exception {
		try {
			valIdNotNull(profile);
			ValIdExist(profile.getProfileId());
			valBkNotNull(profile.getEmail(), profile.getPhone());
			ValNonBk(profile);
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
				if(profile.getProfileId()==null) {
					throw new Exception("Profile Id null");
				}
				return null;
			}
			
			//BK not null
			private Exception valBkNotNull(String email, String phone) throws Exception{
				if(email == null || phone == null) {
					throw new Exception("BK Profile is null");
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
				if(profile.getProfileName()==null ||
						profile.getAddress()==null ||
						profile.getGender()==null ||
						profile.getIsActive()==null) {
					throw new Exception("There is empty field in Profile");
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
