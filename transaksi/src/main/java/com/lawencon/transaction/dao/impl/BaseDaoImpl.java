package com.lawencon.transaction.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDaoImpl {

	@PersistenceContext
	protected EntityManager em;

	protected <T> T saveInsert(T data) throws Exception {
		em.persist(data);
		return data;
	}

	protected <T> T saveUpdate(T data) throws Exception {
		T dataMerge = em.merge(data);
		em.flush();
		return dataMerge;
	}

	protected <T> void deleteById(Class<T> clazz, Long id) throws Exception {
		T removedEntity = em.find(clazz, id);
		em.remove(removedEntity);
	}

	protected <T> T getById(Class<T> clazz, Long id) throws Exception {
		return em.find(clazz, id);
	}
}
