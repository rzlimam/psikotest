package com.lawencon.psikotest.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EntityDao {
	
	@PersistenceContext
	protected
	EntityManager entityManager;

}
