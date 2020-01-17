package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.Profile;

@Repository("profileDao")
public class ProfileDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Profile> getAll() {
		List<Profile> list = super.entityManager
				.createQuery("From Profile")
				.getResultList();
		return list;
	}
	
	@Transactional
	public Profile save(Profile profile) {
		return super.entityManager.merge(profile);
		
	}
	
	@Transactional
	public void delete(String id) {
		Profile profile = findById(id);
		super.entityManager.remove(profile);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Profile findById(String id) {
		List<Profile> list = super.entityManager
				.createQuery("From Profile where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (Profile)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Profile findByBk(String email, String phone) {
		List<Profile> list = super.entityManager
				.createQuery("From Profile where email=:email and phone=:phone")
				.setParameter("email", email)
				.setParameter("phone", phone)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (Profile)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Profile> findByName(String search) {
		List<Profile> list = super.entityManager
				.createQuery("From Profile where lower(profile_name) like "
						+ "concat('%', :search, '%')")
				.setParameter("search", search.toLowerCase())
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Profile> findByEmail(String search) {
		List<Profile> list = super.entityManager
				.createQuery("From Profile where lower(email) like "
						+ "concat('%', :search, '%')")
				.setParameter("search", search.toLowerCase())
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Profile> findByPhone(String search) {
		List<Profile> list = super.entityManager
				.createQuery("From Profile where lower(phone) like "
						+ "concat('%', :search, '%')")
				.setParameter("search", search.toLowerCase())
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return list;
	}

}
