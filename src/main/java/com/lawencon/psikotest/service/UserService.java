package com.lawencon.psikotest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.UserDao;
import com.lawencon.psikotest.entity.User;
import com.lawencon.psikotest.entity.UserList;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private RoleService roleService;
	
	public UserList findById(String id) {
		User account = userDao.findById(id);
		UserList user = new UserList();
		user.setUserId(account.getUserId());
		user.setProfile(account.getProfile());
		user.setRole(account.getRole());
		user.setActive(account.getIsActive());
		return user;
	}
	
	public List<UserList> getAll(){
		List<User> list = userDao.getAll();
		List<UserList> users = new ArrayList<UserList>();
		for (User u : list) {
			UserList user = new UserList();
			user.setUserId(u.getUserId());
			user.setProfile(u.getProfile());
			user.setRole(u.getRole());
			user.setActive(u.getIsActive());
			users.add(user);
		}
		return users;
	}
	
	public UserList findByBk(String profileId) {
		User account = userDao.findById(profileId);
		UserList user = new UserList();
		user.setUserId(account.getUserId());
		user.setProfile(account.getProfile());
		user.setRole(account.getRole());
		user.setActive(account.getIsActive());
		return user;
	}
	
	public UserList findByEmail(String email) {
		User account = userDao.findEmail(email);
		UserList user = new UserList();
		user.setUserId(account.getUserId());
		user.setProfile(account.getProfile());
		user.setRole(account.getRole());
		user.setActive(account.getIsActive());
		return user;
	}
	
	public UserList login(String email, String password) throws Exception {
		User account = userDao.findEmail(email);
		if(account!=null) {
			String userPass = account.getPassword();
			if(password.equals(userPass)) {
				UserList user = new UserList();
				user.setUserId(account.getUserId());
				user.setProfile(account.getProfile());
				user.setRole(account.getRole());
				user.setActive(account.getIsActive());
				return user;
			} else {
				throw new Exception("Password salah");
			}
		}
		throw new Exception("Username belum tedaftar");
		
	}
	
	
	public void insert(User user) throws Exception {
		try {
			valIdNull(user);
			valBkNotNull(user);
			valBkNotExist(user);
			ValProfileExist(user.getProfile().getProfileId());
			ValRoleExist(user.getRole().getRoleId());
			ValNonBk(user);
			userDao.save(user);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void update(User user) throws Exception {
		try {
			valIdNotNull(user);
			ValIdExist(user.getUserId());
			valBkNotNull(user);
			ValBkNotChange(user);
			ValNonBk(user);
			userDao.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public void delete(String id)throws Exception {
		try {
			ValIdExist(id);
			userDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public String getPassword() 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "0123456789abcdefghijklmnopqrstuvwxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(8); 
  
        for (int i = 0; i < 8; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	
	//Validasi
	
		//Id null
		private Exception valIdNull(User user) throws Exception{
			if(user.getUserId()!=null) {
				throw new Exception("User Id not null");
			}
			return null;
		}
		
		//Id null
		private Exception valIdNotNull(User user) throws Exception{
			if(user.getUserId()==null) {
				throw new Exception("User Id null");
			}
			return null;
		}
		
		//BK not null
		private Exception valBkNotNull(User user) throws Exception{
			if(user.getProfile().getProfileId()==null) {
				throw new Exception("Profile is null");
			}
			return null;
		}
		
		//BK not exist
		private Exception valBkNotExist(User user) throws Exception{
			if(findByBk(user.getProfile().getProfileId())!=null) {
				throw new Exception("User Code is Exist");
			}
			return null;
		}
		
		//NonBk not null
		private Exception ValNonBk(User user) throws Exception {
			if(user.getRole().getRoleId()==null || user.getRole().getRoleId().trim().equals("")) {
				throw new Exception("Role is empty");
			} else if(user.getPassword().trim().equals("")) {
				throw new Exception("Password is empty");
			}
			return null;
		}
		
		//Bk not change
		private Exception ValBkNotChange(User user) throws Exception {
			if(!user.getProfile().getProfileId().equalsIgnoreCase(user.getProfile().getProfileId())) {
				throw new Exception("Profile is Change");
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
		
		//Profile Exist
		private Exception ValProfileExist(String id) throws Exception {
			if(profileService.findById(id)==null) {
				throw new Exception("Id Profile is not Exist");
			}
			return null;
		}
		
		//Role Exist
		private Exception ValRoleExist(String id) throws Exception {
			if(roleService.findById(id)==null) {
				throw new Exception("Id Role is not Exist");
			}
			return null;
		}
		

}
