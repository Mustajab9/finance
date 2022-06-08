package com.lawencon.master.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.master.dao.ChartOfAccountDao;
import com.lawencon.master.model.ChartOfAccount;

@Repository
public class ChartOfAccountDaoImpl extends BaseDaoImpl implements ChartOfAccountDao {

	@Override
	public List<ChartOfAccount> getAll() throws Exception {
		List<ChartOfAccount> data = em.createQuery("FROM ChartOfAccount", ChartOfAccount.class).getResultList();

		return data;
	}

	@Override
	public ChartOfAccount getById(Long id) throws Exception {
		return super.getById(ChartOfAccount.class, id);
	}

	@Override
	public ChartOfAccount insert(ChartOfAccount data) throws Exception {
		return super.saveInsert(data);
	}

	@Override
	public ChartOfAccount update(ChartOfAccount data) throws Exception {
		return super.saveUpdate(data);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		super.deleteById(ChartOfAccount.class, id);
	}

	@Override
	public List<ChartOfAccount> getVoucherTransaction() throws Exception {
		String sql = "SELECT id, coa_code, coa_name"
				+ " FROM coa"
				+ " WHERE coa_parent = 4 OR coa_parent = 7";

		List<?> results = em.createNativeQuery(sql).getResultList();

		List<ChartOfAccount> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			ChartOfAccount data = new ChartOfAccount();

			data.setId(Long.valueOf(obj[0].toString()));
			data.setCoaCode(obj[1].toString());
			data.setCoaName(obj[2].toString());
			
			listResult.add(data);
		});
		
		return listResult;
	}
}
