package com.lawencon.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.master.constant.ResponseMessageType;
import com.lawencon.master.dao.JurnalTypeDao;
import com.lawencon.master.dto.DeleteDtoRes;
import com.lawencon.master.dto.InsertDataDtoRes;
import com.lawencon.master.dto.InsertDtoRes;
import com.lawencon.master.dto.UpdateDataDtoRes;
import com.lawencon.master.dto.UpdateDtoRes;
import com.lawencon.master.dto.jurnaltype.GetAllTypeDtoDataRes;
import com.lawencon.master.dto.jurnaltype.GetAllTypeDtoRes;
import com.lawencon.master.dto.jurnaltype.GetByTypeIdDtoDataRes;
import com.lawencon.master.dto.jurnaltype.GetByTypeIdDtoRes;
import com.lawencon.master.dto.jurnaltype.InsertTypeDtoReq;
import com.lawencon.master.dto.jurnaltype.UpdateTypeDtoReq;
import com.lawencon.master.model.JurnalType;
import com.lawencon.master.service.JurnalTypeService;

@Service
public class JurnalTypeServiceImpl extends BaseServiceImpl implements JurnalTypeService {
	private JurnalTypeDao jurnalTypeDao;

	@Autowired
	public JurnalTypeServiceImpl(JurnalTypeDao jurnalTypeDao) {
		this.jurnalTypeDao = jurnalTypeDao;
	}


	@Override
	public GetAllTypeDtoRes getAll() throws Exception {
		GetAllTypeDtoRes getAll = new GetAllTypeDtoRes();

		List<JurnalType> types = jurnalTypeDao.getAll();
		List<GetAllTypeDtoDataRes> listType = new ArrayList<>();

		int length = types.size();
		for (int i = 0; i < length; i++) {
			JurnalType type = types.get(i);
			GetAllTypeDtoDataRes data = new GetAllTypeDtoDataRes();

			data.setId(type.getId());
			data.setTypeCode(type.getTypeCode());
			data.setTypeName(type.getTypeName());
			data.setVersion(type.getVersion());
			data.setIsActive(type.getIsActive());

			listType.add(data);
		}

		getAll.setData(listType);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetByTypeIdDtoRes getById(Long id) throws Exception {
		GetByTypeIdDtoRes getById = new GetByTypeIdDtoRes();

		JurnalType type = jurnalTypeDao.getById(id);
		
		if(type == null) {
			throw new Exception("Type With Id " + id + " Not Found ");
		}
		
		GetByTypeIdDtoDataRes data = new GetByTypeIdDtoDataRes();

		data.setId(type.getId());
		data.setTypeCode(type.getTypeCode());
		data.setTypeName(type.getTypeName());
		data.setVersion(type.getVersion());
		data.setIsActive(type.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertTypeDtoReq data) throws Exception {
		JurnalType type = new JurnalType();
		
		type.setTypeCode(data.getTypeCode());
		type.setTypeName(data.getTypeName());
		type.setCreatedBy(loginId());

		JurnalType typeSave = jurnalTypeDao.insert(type);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(typeSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateTypeDtoReq data) throws Exception {
		UpdateDtoRes response = new UpdateDtoRes();

		
		JurnalType type = jurnalTypeDao.getById(data.getId());

		type.setTypeCode(data.getTypeCode());
		type.setTypeName(data.getTypeName());
		type.setVersion(data.getVersion());
		type.setUpdatedBy(loginId());

		if (data.getIsActive() != null) {
			type.setIsActive(data.getIsActive());
		}

		JurnalType typeSave = jurnalTypeDao.update(type);

		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(typeSave.getVersion());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());
		
		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(Long id) throws Exception {
		jurnalTypeDao.deleteById(id);

		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());

		return response;
	}
}
