package com.lawencon.master.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.master.dao.JurnalTypeDao;
import com.lawencon.master.model.JurnalType;

@Repository
public class JurnalTypeDaoImpl extends BaseDaoImpl implements JurnalTypeDao {
	
	@Override
	public List<JurnalType> getAll() throws Exception {
		List<JurnalType> data = em.createQuery("FROM JurnalType", JurnalType.class).getResultList();
		
		return data;
	}
	
	@Override
	public JurnalType getById(Long id) throws Exception {
		return super.getById(JurnalType.class, id);
	}
	
	@Override
	public JurnalType insert(JurnalType data) throws Exception {
		return super.saveInsert(data);
	}
	
	@Override
	public JurnalType update(JurnalType data) throws Exception {
		return super.saveUpdate(data);
	}
	
	@Override
	public void deleteById(Long id) throws Exception {
		super.deleteById(JurnalType.class, id);
	}
}
