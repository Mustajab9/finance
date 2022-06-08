package com.lawencon.transaction.dao;

import java.util.List;

import com.lawencon.transaction.model.GeneralLedger;

public interface GeneralLedgerDao extends BaseDao<GeneralLedger> {
	List<GeneralLedger> getSaldoGL(Long id) throws Exception;
}
