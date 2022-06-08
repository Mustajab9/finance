package com.lawencon.transaction.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.transaction.dao.GeneralLedgerDao;
import com.lawencon.transaction.model.GeneralLedger;

@Repository
public class GeneralLedgerDaoImpl extends BaseDaoImpl implements GeneralLedgerDao {
	
	@Override
	public List<GeneralLedger> getAll() throws Exception {
		List<GeneralLedger> data = em.createQuery("FROM GeneralLedger", GeneralLedger.class).getResultList();
		
		return data;
	}
	
	@Override
	public GeneralLedger getById(Long id) throws Exception {
		return super.getById(GeneralLedger.class, id);
	}
	
	@Override
	public GeneralLedger insert(GeneralLedger data) throws Exception {
		return super.saveInsert(data);
	}
	
	@Override
	public GeneralLedger update(GeneralLedger data) throws Exception {
		return super.saveUpdate(data);
	}
	
	@Override
	public void deleteById(Long id) throws Exception {
		super.deleteById(GeneralLedger.class, id);
	}
}
