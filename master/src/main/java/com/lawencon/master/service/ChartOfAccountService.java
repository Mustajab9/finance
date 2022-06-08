package com.lawencon.master.service;

import com.lawencon.master.dto.InsertDtoRes;
import com.lawencon.master.dto.UpdateDtoRes;
import com.lawencon.master.dto.chartofaccount.GetAllCoADtoRes;
import com.lawencon.master.dto.chartofaccount.GetByCoAIdDtoRes;
import com.lawencon.master.dto.chartofaccount.GetCoAVoucherTransactionDtoRes;
import com.lawencon.master.dto.chartofaccount.InsertCoADtoReq;
import com.lawencon.master.dto.chartofaccount.UpdateCoADtoReq;

public interface ChartOfAccountService extends BaseSevice {
	GetAllCoADtoRes getAll() throws Exception;

	GetByCoAIdDtoRes getById(Long id) throws Exception;

	InsertDtoRes insert(InsertCoADtoReq data) throws Exception;

	UpdateDtoRes update(UpdateCoADtoReq data) throws Exception;
	
	GetCoAVoucherTransactionDtoRes getVoucherTransaction() throws Exception;
}
