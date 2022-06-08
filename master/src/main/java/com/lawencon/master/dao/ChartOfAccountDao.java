package com.lawencon.master.dao;

import java.util.List;

import com.lawencon.master.model.ChartOfAccount;

public interface ChartOfAccountDao extends BaseDao<ChartOfAccount> {
	List<ChartOfAccount> getVoucherTransaction() throws Exception;
}
