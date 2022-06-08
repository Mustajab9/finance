package com.lawencon.transaction.service;

import com.lawencon.transaction.dto.InsertDtoRes;
import com.lawencon.transaction.dto.voucher.GetAllVoucherDtoRes;
import com.lawencon.transaction.dto.voucher.GetByVoucherIdDtoRes;
import com.lawencon.transaction.dto.voucher.InsertVoucherDtoReq;

public interface VoucherService {
	GetAllVoucherDtoRes getAll() throws Exception;

	GetByVoucherIdDtoRes getById(Long id) throws Exception;

	InsertDtoRes insert(InsertVoucherDtoReq data) throws Exception;
}
