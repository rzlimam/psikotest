package com.lawencon.psikotest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lawencon.psikotest.entity.Packages;

@Repository("packageDao")
public class PackageDao extends EntityDao {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Packages> getAll() {
		List<Packages> list = super.entityManager
				.createQuery("From Packages")
				.getResultList();
		return list;
	}
	
	@Transactional
	public Packages save(Packages pack) {
		return super.entityManager.merge(pack);
		
	}
	
	@Transactional
	public void delete(String id) {
		Packages pack = findById(id);
		super.entityManager.remove(pack);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Packages findById(String id) {
		List<Packages> list = super.entityManager
				.createQuery("From Packages where id=:id")
				.setParameter("id", id)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (Packages)list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Packages findByBk(String packageName) {
		List<Packages> list = super.entityManager
				.createQuery("From Packages where packageName=:packageName")
				.setParameter("packageName", packageName)
				.getResultList();
		if(list.size() == 0)
			return null;
		else 
			return (Packages)list.get(0);
	}

}