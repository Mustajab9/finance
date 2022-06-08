package com.lawencon.transaction.dao.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.transaction.dao.GeneralLedgerDao;
import com.lawencon.transaction.model.GeneralLedger;
import com.lawencon.transaction.model.Voucher;

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

	@Override
	public List<GeneralLedger> getSaldoGL(Long id) throws Exception {
		String sql = "SELECT id, transaction_code, transaction_date, description,"
				+ " voucher_id, coa_id, coa_code, coa_name, debit, credit, saldo,"
				+ " version, is_active "
				+ " FROM get_balance_gl(:id) "
				+ "	ORDER BY transaction_date";

		List<?> results = em.createNativeQuery(sql).setParameter("id", BigInteger.valueOf(id)).getResultList();

		List<GeneralLedger> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GeneralLedger data = new GeneralLedger();

			data.setId(Long.valueOf(obj[0].toString()));
			data.setTransactionCode(obj[1].toString());
			data.setTransactionDate(((Date) obj[2]).toLocalDate());

			if (obj[3] != null) {
				data.setDescription(obj[3].toString());
			}

			if (obj[4] != null) {
				Voucher voucher = new Voucher();
				voucher.setId(Long.valueOf(obj[4].toString()));

				data.setVoucherId(voucher);
			}

			data.setCoaId(Long.valueOf(obj[5].toString()));
			data.setCoaCode(obj[6].toString());
			data.setCoaName(obj[7].toString());
			data.setDebit(Double.valueOf(obj[8].toString()));
			data.setCredit(Double.valueOf(obj[9].toString()));
			data.setSaldo(Double.valueOf(obj[10].toString()));
			data.setVersion(Integer.valueOf(obj[11].toString()));
			data.setIsActive(Boolean.valueOf(obj[12].toString()));

			listResult.add(data);
		});

		return listResult;
	}
}
