package com.lawencon.psikotest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.psikotest.dao.RoleDao;
import com.lawencon.psikotest.entity.Role;

@Service("roleService")
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public Role findById(String id) {
		Role role = roleDao.findById(id);
		return role;
	}
	
	public Role findByCode(String code) {
		Role role = roleDao.findByCode(code);
		return role;
	}
	
	public List<Role> getAll(){
		List<Role> list = roleDao.getAll();
		return list;
	}
	
	public void insert(Role role) throws Exception {
		try {
			valIdNull(role);
			valBkNotNull(role);
			valBkNotExist(role);
			ValNonBk(role);
			roleDao.save(role);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(Role role) throws Exception {
		try {
			valIdNotNull(role);
			ValIdExist(role.getRoleId());
			valBkNotNull(role);
			ValBkNotChange(role);
			ValNonBk(role);
			roleDao.save(role);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public void delete(String id) throws Exception {
		try {
			ValIdExist(id);
			roleDao.delete(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	//Validasi
	
		//Id null
		private Exception valIdNull(Role role) throws Exception{
			if(role.getRoleId()!=null) {
				throw new Exception("Role Id not null");
			}
			return null;
		}
		
		//Id null
		private Exception valIdNotNull(Role role) throws Exception{
			if(role.getRoleId()==null) {
				throw new Exception("Role Id null");
			}
			return null;
		}
		
		//BK not null
		private Exception valBkNotNull(Role role) throws Exception{
			if(role.getCodeRole()==null) {
				throw new Exception("Role Code is null");
			}
			return null;
		}
		
		//BK not exist
		private Exception valBkNotExist(Role role) throws Exception{
			if(findByCode(role.getCodeRole())!=null) {
				throw new Exception("Role Code is Exist");
			}
			return null;
		}
		
		//NonBk not null
		private Exception ValNonBk(Role role) throws Exception {
			if(role.getNamaRole()==null || role.getNamaRole().trim().equals("")) {
				throw new Exception("Role Name is empty");
			} else if(role.getIsActive()==null) {
				throw new Exception("Active State is empty");
			}
			return null;
		}
		
		//Bk not change
		private Exception ValBkNotChange(Role role) throws Exception {
			if(!findById(role.getRoleId()).getCodeRole().equalsIgnoreCase(role.getCodeRole())) {
				throw new Exception("Role Code is Change");
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

}
