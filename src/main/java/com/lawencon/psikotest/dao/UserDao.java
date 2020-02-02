package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.User;

@Repository("userDao")
public class UserDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllRecruiter() {
		List<User> list = super.entityManager
				.createQuery("From User WHERE role.codeRole=:code "
						+ "and isActive=:isActive")
				.setParameter("code", "REC")
				.setParameter("isActive", true)
				.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllCandidate() {
		List<User> list = super.entityManager
				.createQuery("From User WHERE role.codeRole=:code "
						+ "and isActive=:isActive")
				.setParameter("code", "CAN")
				.setParameter("isActive", true)
				.getResultList();
		return list;
	}
	
	@Transactional
	public User save(User user) {
		return super.entityManager.merge(user);
	}
	
	@Transactional
	public void delete(String id) {
		User user = findById(id);
		super.entityManager.remove(user);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User findById(String id) {
		List<User> list = super.entityManager
				.createQuery("From User where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (User)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User findByBk(String profileId) {
		List<User> list = super.entityManager
				.createQuery("From User where profile_id=:profile_id")
				.setParameter("profile_id", profileId)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (User)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public User findEmail(String email) {
		List<User> list = super.entityManager
				.createQuery("From User where profile.email=:email")
				.setParameter("email", email)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (User)list.get(0);
	}

}
