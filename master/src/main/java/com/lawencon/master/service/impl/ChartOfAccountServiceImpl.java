package com.lawencon.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.master.constant.ResponseMessageType;
import com.lawencon.master.dao.ChartOfAccountDao;
import com.lawencon.master.dao.JurnalTypeDao;
import com.lawencon.master.dto.DeleteDtoRes;
import com.lawencon.master.dto.InsertDataDtoRes;
import com.lawencon.master.dto.InsertDtoRes;
import com.lawencon.master.dto.UpdateDataDtoRes;
import com.lawencon.master.dto.UpdateDtoRes;
import com.lawencon.master.dto.chartofaccount.GetAllCoADtoDataRes;
import com.lawencon.master.dto.chartofaccount.GetAllCoADtoRes;
import com.lawencon.master.dto.chartofaccount.GetByCoAIdDtoDataRes;
import com.lawencon.master.dto.chartofaccount.GetByCoAIdDtoRes;
import com.lawencon.master.dto.chartofaccount.GetCoAVoucherTransactionDtoDataRes;
import com.lawencon.master.dto.chartofaccount.GetCoAVoucherTransactionDtoRes;
import com.lawencon.master.dto.chartofaccount.InsertCoADtoReq;
import com.lawencon.master.dto.chartofaccount.UpdateCoADtoReq;
import com.lawencon.master.model.ChartOfAccount;
import com.lawencon.master.model.JurnalType;
import com.lawencon.master.service.ChartOfAccountService;

@Service
public class ChartOfAccountServiceImpl extends BaseServiceImpl implements ChartOfAccountService {
	private ChartOfAccountDao coADao;
	private JurnalTypeDao typeDao;

	@Autowired
	public ChartOfAccountServiceImpl(ChartOfAccountDao coADao, JurnalTypeDao typeDao) {
		this.coADao = coADao;
		this.typeDao = typeDao;
	}

	@Override
	public GetAllCoADtoRes getAll() throws Exception {
		GetAllCoADtoRes getAll = new GetAllCoADtoRes();

		List<ChartOfAccount> coas = coADao.getAll();
		List<GetAllCoADtoDataRes> ListCoAs = new ArrayList<>();

		int length = coas.size();
		for (int i = 0; i < length; i++) {
			ChartOfAccount coa = coas.get(i);
			GetAllCoADtoDataRes data = new GetAllCoADtoDataRes();

			data.setId(coa.getId());
			data.setCoaParent(coa.getCoaParent());
			data.setCoaCode(coa.getCoaCode());
			data.setCoaName(coa.getCoaName());
			data.setTypeId(coa.getTypeId().getId());
			data.setTypeName(coa.getTypeId().getTypeName());
			data.setVersion(coa.getVersion());
			data.setIsActive(coa.getIsActive());

			ListCoAs.add(data);
		}

		getAll.setData(ListCoAs);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetByCoAIdDtoRes getById(Long id) throws Exception {
		GetByCoAIdDtoRes getById = new GetByCoAIdDtoRes();

		ChartOfAccount coa = coADao.getById(id);

		if (coa == null) {
			throw new Exception("Coa Wiht Id " + id + " Not Found ");
		}

		GetByCoAIdDtoDataRes data = new GetByCoAIdDtoDataRes();

		data.setId(coa.getId());
		data.setCoaParent(coa.getCoaParent());
		data.setCoaCode(coa.getCoaCode());
		data.setCoaName(coa.getCoaName());
		data.setTypeId(coa.getTypeId().getId());
		data.setTypeName(coa.getTypeId().getTypeName());
		data.setVersion(coa.getVersion());
		data.setIsActive(coa.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertCoADtoReq data) throws Exception {
		ChartOfAccount coa = new ChartOfAccount();

		coa.setCoaParent(data.getCoaParent());
		coa.setCoaCode(data.getCoaCode());
		coa.setCoaName(data.getCoaName());

		JurnalType type = typeDao.getById(data.getTypeId());
		coa.setTypeId(type);

		coa.setCreatedBy(loginId());

		ChartOfAccount coaSave = coADao.insert(coa);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(coaSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateCoADtoReq data) throws Exception {
		UpdateDtoRes response = new UpdateDtoRes();

		ChartOfAccount coa = coADao.getById(data.getId());

		coa.setCoaParent(data.getCoaParent());
		coa.setCoaCode(data.getCoaCode());
		coa.setCoaName(data.getCoaName());

		JurnalType type = typeDao.getById(data.getTypeId());
		coa.setTypeId(type);

		coa.setVersion(data.getVersion());

		coa.setUpdatedBy(loginId());

		if (data.getIsActive() != null) {
			coa.setIsActive(data.getIsActive());
		}

		ChartOfAccount coaSave = coADao.update(coa);

		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(coaSave.getVersion());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(Long id) throws Exception {
		coADao.deleteById(id);

		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());

		return response;
	}

	@Override
	public GetCoAVoucherTransactionDtoRes getVoucherTransaction() throws Exception {
		GetCoAVoucherTransactionDtoRes getVoucherTransaction = new GetCoAVoucherTransactionDtoRes();

		List<ChartOfAccount> coas = coADao.getVoucherTransaction();
		List<GetCoAVoucherTransactionDtoDataRes> ListCoAs = new ArrayList<>();

		int length = coas.size();
		for (int i = 0; i < length; i++) {
			ChartOfAccount coa = coas.get(i);
			GetCoAVoucherTransactionDtoDataRes data = new GetCoAVoucherTransactionDtoDataRes();

			data.setId(coa.getId());
			data.setCoaCode(coa.getCoaCode());
			data.setCoaName(coa.getCoaName());

			ListCoAs.add(data);
		}

		getVoucherTransaction.setData(ListCoAs);
		getVoucherTransaction.setMsg(null);

		return getVoucherTransaction;
	}
}
