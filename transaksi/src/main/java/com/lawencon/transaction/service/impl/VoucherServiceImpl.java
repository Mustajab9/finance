package com.lawencon.transaction.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.transaction.constant.ResponseMessageType;
import com.lawencon.transaction.dao.VoucherDao;
import com.lawencon.transaction.dto.InsertDataDtoRes;
import com.lawencon.transaction.dto.InsertDtoRes;
import com.lawencon.transaction.dto.voucher.GetAllVoucherDtoDataRes;
import com.lawencon.transaction.dto.voucher.GetAllVoucherDtoRes;
import com.lawencon.transaction.dto.voucher.GetByVoucherIdDtoDataRes;
import com.lawencon.transaction.dto.voucher.GetByVoucherIdDtoRes;
import com.lawencon.transaction.dto.voucher.InsertVoucherDtoReq;
import com.lawencon.transaction.model.Voucher;
import com.lawencon.transaction.pojo.ChartOfAccountDataPojo;
import com.lawencon.transaction.pojo.ChartOfAccountPojo;
import com.lawencon.transaction.service.VoucherService;

@Service
public class VoucherServiceImpl extends BaseServiceImpl implements VoucherService {
	private VoucherDao voucherDao;

	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;

	@Autowired
	public VoucherServiceImpl(VoucherDao voucherDao) {
		this.voucherDao = voucherDao;
	}

	@Override
	public GetAllVoucherDtoRes getAll() throws Exception {
		GetAllVoucherDtoRes getAll = new GetAllVoucherDtoRes();

		List<Voucher> vouchers = voucherDao.getAll();
		List<GetAllVoucherDtoDataRes> ListVoucher = new ArrayList<>();

		int length = vouchers.size();
		for (int i = 0; i < length; i++) {
			Voucher voucher = vouchers.get(i);
			GetAllVoucherDtoDataRes data = new GetAllVoucherDtoDataRes();

			data.setId(voucher.getId());
			data.setTransactionCode(voucher.getTransactionCode());
			data.setTransactionDate(voucher.getTransactionDate());
			data.setTotalTransaction(voucher.getTotalTransaction());

			if (voucher.getDescription() != null) {
				data.setDescription(voucher.getDescription());
			}

			data.setVersion(voucher.getVersion());
			data.setIsActive(voucher.getIsActive());

			data.setCoaId(voucher.getCoaId());
			data.setCoaCode(voucher.getCoaCode());
			data.setCoaName(voucher.getCoaName());

			ChartOfAccountPojo coaPojo = restTemplate.getForObject(
					"http://MASTER-SERVICE/master/chart-of-accounts/" + data.getCoaId(), ChartOfAccountPojo.class);

			ChartOfAccountDataPojo coaData = coaPojo.getData();
			
			if (coaData.getCoaParent() != null) {
				data.setCoaParent(coaData.getCoaParent());

				ChartOfAccountPojo parentPojo = restTemplate.getForObject(
						"http://MASTER-SERVICE/master/chart-of-accounts/" + coaData.getCoaParent(), ChartOfAccountPojo.class);

				ChartOfAccountDataPojo parentData = parentPojo.getData();

				if (parentData != null) {
					data.setCoaParentCode(parentData.getCoaCode());
					data.setCoaParentName(parentData.getCoaName());
				}
			}

			ListVoucher.add(data);
		}

		getAll.setData(ListVoucher);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetByVoucherIdDtoRes getById(Long id) throws Exception {
		GetByVoucherIdDtoRes getById = new GetByVoucherIdDtoRes();

		Voucher voucher = voucherDao.getById(id);
		
		if(voucher == null) {
			throw new Exception("Voucher With id " + id + " Not Found ");
		}
		
		GetByVoucherIdDtoDataRes data = new GetByVoucherIdDtoDataRes();

		data.setId(voucher.getId());
		data.setTransactionCode(voucher.getTransactionCode());
		data.setTransactionDate(voucher.getTransactionDate());
		data.setTotalTransaction(voucher.getTotalTransaction());

		if (voucher.getDescription() != null) {
			data.setDescription(voucher.getDescription());
		}

		data.setVersion(voucher.getVersion());
		data.setIsActive(voucher.getIsActive());

		data.setCoaId(voucher.getCoaId());
		data.setCoaCode(voucher.getCoaCode());
		data.setCoaName(voucher.getCoaName());

		ChartOfAccountPojo coaPojo = restTemplate.getForObject(
				"http://MASTER-SERVICE/master/chart-of-accounts/" + data.getCoaId(), ChartOfAccountPojo.class);

		ChartOfAccountDataPojo coaData = coaPojo.getData();
		
		if (coaData.getCoaParent() != null) {
			data.setCoaParent(coaData.getCoaParent());

			ChartOfAccountPojo parentPojo = restTemplate.getForObject(
					"http://MASTER-SERVICE/master/chart-of-accounts/" + coaData.getCoaParent(), ChartOfAccountPojo.class);

			ChartOfAccountDataPojo parentData = parentPojo.getData();
			
			if (parentData != null) {
				data.setCoaParentCode(parentData.getCoaCode());
				data.setCoaParentName(parentData.getCoaName());
			}
		}

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertVoucherDtoReq data) throws Exception {
		Voucher voucher = new Voucher();

		if (data.getCoaId() == null) {
			throw new Exception("Chart Of Account Cant Null ");
		}

		ChartOfAccountPojo coaPojo = restTemplate.getForObject(
				"http://MASTER-SERVICE/master/chart-of-accounts/" + data.getCoaId(), ChartOfAccountPojo.class);

		ChartOfAccountDataPojo coaData = coaPojo.getData();
		
		voucher.setCoaId(data.getCoaId());
		voucher.setCoaCode(coaData.getCoaCode());
		voucher.setCoaName(coaData.getCoaName());
		voucher.setTransactionCode(data.getTransactionCode());

		LocalDate transactionDate = convertDateToLocalDate(data.getTransactionDate());
		voucher.setTransactionDate(transactionDate);

		if (data.getDescription() != null) {
			voucher.setDescription(data.getDescription());
		}

		voucher.setTotalTransaction(data.getTotalTransaction());
		voucher.setCreatedBy(loginId());

		Voucher voucherSave = voucherDao.insert(voucher);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(voucherSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}
}
