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
import com.lawencon.transaction.dao.GeneralLedgerDao;
import com.lawencon.transaction.dao.VoucherDao;
import com.lawencon.transaction.dto.InsertDataDtoRes;
import com.lawencon.transaction.dto.InsertDtoRes;
import com.lawencon.transaction.dto.gl.GetAllGLDtoDataRes;
import com.lawencon.transaction.dto.gl.GetAllGLDtoRes;
import com.lawencon.transaction.dto.gl.GetByGLIdDtoDataRes;
import com.lawencon.transaction.dto.gl.GetByGLIdDtoRes;
import com.lawencon.transaction.dto.gl.InsertGLDtoReq;
import com.lawencon.transaction.model.GeneralLedger;
import com.lawencon.transaction.model.Voucher;
import com.lawencon.transaction.pojo.ChartOfAccountDataPojo;
import com.lawencon.transaction.pojo.ChartOfAccountPojo;
import com.lawencon.transaction.service.GeneralLedgerService;

@Service
public class GeneralLedgerServiceImpl extends BaseServiceImpl implements GeneralLedgerService {
	private GeneralLedgerDao generalLedgerDao;
	private VoucherDao voucherDao;

	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;

	@Autowired
	public GeneralLedgerServiceImpl(GeneralLedgerDao generalLedgerDao, VoucherDao voucherDao) {
		this.generalLedgerDao = generalLedgerDao;
		this.voucherDao = voucherDao;
	}

	@Override
	public GetAllGLDtoRes getAll() throws Exception {
		GetAllGLDtoRes getAll = new GetAllGLDtoRes();

		List<GeneralLedger> gls = generalLedgerDao.getAll();
		List<GetAllGLDtoDataRes> ListGL = new ArrayList<>();

		int length = gls.size();
		for (int i = 0; i < length; i++) {
			GeneralLedger gl = gls.get(i);
			GetAllGLDtoDataRes data = new GetAllGLDtoDataRes();

			data.setId(gl.getId());
			data.setTransactionCode(gl.getTransactionCode());
			data.setTransactionDate(gl.getTransactionDate());
			data.setDebit(gl.getDebit());
			data.setCredit(gl.getCredit());

			if (gl.getDescription() != null) {
				data.setDescription(gl.getDescription());
			}

			data.setVersion(gl.getVersion());
			data.setIsActive(gl.getIsActive());

			data.setCoaId(gl.getCoaId());
			data.setCoaCode(gl.getCoaCode());
			data.setCoaName(gl.getCoaName());

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

			ListGL.add(data);
		}

		getAll.setData(ListGL);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetByGLIdDtoRes getById(Long id) throws Exception {
		GetByGLIdDtoRes getById = new GetByGLIdDtoRes();

		GeneralLedger gl = generalLedgerDao.getById(id);

		if (gl == null) {
			throw new Exception("General Ledger With id " + id + " Not Found ");
		}

		GetByGLIdDtoDataRes data = new GetByGLIdDtoDataRes();

		data.setId(gl.getId());
		data.setTransactionCode(gl.getTransactionCode());
		data.setTransactionDate(gl.getTransactionDate());
		data.setDebit(gl.getDebit());
		data.setCredit(gl.getCredit());

		if (gl.getDescription() != null) {
			data.setDescription(gl.getDescription());
		}

		data.setVersion(gl.getVersion());
		data.setIsActive(gl.getIsActive());

		data.setCoaId(gl.getCoaId());
		data.setCoaCode(gl.getCoaCode());
		data.setCoaName(gl.getCoaName());

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
	public InsertDtoRes insert(InsertGLDtoReq data) throws Exception {
		GeneralLedger gl = new GeneralLedger();

		if (data.getCoaId() == null) {
			throw new Exception("Chart of Account Cant Null");
		}

		ChartOfAccountPojo coaPojo = restTemplate.getForObject(
				"http://MASTER-SERVICE/master/chart-of-accounts/" + data.getCoaId(), ChartOfAccountPojo.class);

		ChartOfAccountDataPojo coaData = coaPojo.getData();

		gl.setCoaId(data.getCoaId());
		gl.setCoaCode(coaData.getCoaCode());
		gl.setCoaName(coaData.getCoaName());
		gl.setTransactionCode(data.getTransactionCode());

		LocalDate transactionDate = convertDateToLocalDate(data.getTransactionDate());
		gl.setTransactionDate(transactionDate);

		if (data.getDescription() != null) {
			gl.setDescription(data.getDescription());
		}

		if (data.getVoucherId() != null) {
			Voucher voucher = voucherDao.getById(data.getVoucherId());
			gl.setVoucherId(voucher);
		}

		gl.setDebit(data.getDebit());
		gl.setCredit(data.getCredit());
		gl.setCreatedBy(loginId());

		GeneralLedger glSave = generalLedgerDao.insert(gl);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(glSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}
}
