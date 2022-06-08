package com.lawencon.transaction.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.transaction.dao.VoucherDao;
import com.lawencon.transaction.model.Voucher;

@Repository
public class VoucherDaoImpl extends BaseDaoImpl implements VoucherDao {

	@Override
	public List<Voucher> getAll() throws Exception {
		List<Voucher> data = em.createQuery("FROM Voucher", Voucher.class).getResultList();
		
		return data;
	}

	@Override
	public Voucher getById(Long id) throws Exception {
		return super.getById(Voucher.class, id);
	}

	@Override
	public Voucher insert(Voucher data) throws Exception {
		return super.saveInsert(data);
	}

	@Override
	public Voucher update(Voucher data) throws Exception {
		return super.saveUpdate(data);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		super.deleteById(Voucher.class, id);
	}
}
