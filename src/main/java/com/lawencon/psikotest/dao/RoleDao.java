package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.Role;

@Repository("roleDao")
public class RoleDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Role> getAll() {
		List<Role> list = super.entityManager
				.createQuery("From Role")
				.getResultList();
		return list;
	}
	
	@Transactional
	public void save(Role role) {
		super.entityManager.merge(role);
	}
	
	@Transactional
	public void delete(String id) {
		Role role = findById(id);
		super.entityManager.remove(role);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Role findById(String id) {
		List<Role> list = super.entityManager
				.createQuery("From Role where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (Role)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Role findByCode(String code) {
		List<Role> list = super.entityManager
				.createQuery("From Role where codeRole=:code")
				.setParameter("code", code)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (Role)list.get(0);
	}
}
