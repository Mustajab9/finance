package com.lawencon.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.auth.constant.ResponseMessageType;
import com.lawencon.auth.dao.RoleDao;
import com.lawencon.auth.dto.DeleteDtoRes;
import com.lawencon.auth.dto.InsertDataDtoRes;
import com.lawencon.auth.dto.InsertDtoRes;
import com.lawencon.auth.dto.UpdateDataDtoRes;
import com.lawencon.auth.dto.UpdateDtoRes;
import com.lawencon.auth.dto.role.GetAllRoleDtoDataRes;
import com.lawencon.auth.dto.role.GetAllRoleDtoRes;
import com.lawencon.auth.dto.role.GetByRoleIdDtoDataRes;
import com.lawencon.auth.dto.role.GetByRoleIdDtoRes;
import com.lawencon.auth.dto.role.InsertRoleDtoReq;
import com.lawencon.auth.dto.role.UpdateRoleDtoReq;
import com.lawencon.auth.model.Role;
import com.lawencon.auth.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public GetAllRoleDtoRes getAll() throws Exception {
		GetAllRoleDtoRes getAll = new GetAllRoleDtoRes();

		List<Role> roles = roleDao.getAll();
		List<GetAllRoleDtoDataRes> listRole = new ArrayList<>();

		int length = roles.size();
		for (int i = 0; i < length; i++) {
			Role role = roles.get(i);
			GetAllRoleDtoDataRes data = new GetAllRoleDtoDataRes();

			data.setId(role.getId());
			data.setRoleName(role.getRoleName());
			data.setVersion(role.getVersion());
			data.setIsActive(role.getIsActive());

			listRole.add(data);
		}

		getAll.setData(listRole);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetByRoleIdDtoRes getById(Long id) throws Exception {
		GetByRoleIdDtoRes getById = new GetByRoleIdDtoRes();

		Role role = roleDao.getById(id);

		if (role == null) {
			throw new Exception("Role With Id " + id + " Not Found ");
		}

		GetByRoleIdDtoDataRes data = new GetByRoleIdDtoDataRes();

		data.setId(role.getId());
		data.setRoleName(role.getRoleName());
		data.setVersion(role.getVersion());
		data.setIsActive(role.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertRoleDtoReq data) throws Exception {
		Role role = new Role();

		role.setRoleCode(data.getRoleCode());
		role.setRoleName(data.getRoleName());
		role.setCreatedBy(loginId());

		Role roleSave = roleDao.insert(role);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(roleSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateRoleDtoReq data) throws Exception {
		UpdateDtoRes response = new UpdateDtoRes();

		Role role = roleDao.getById(data.getId());

		role.setRoleName(data.getRoleName());
		role.setVersion(data.getVersion());
		role.setUpdatedBy(loginId());

		if (data.getIsActive() != null) {
			role.setIsActive(data.getIsActive());
		}

		Role roleSave = roleDao.update(role);

		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(roleSave.getVersion());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(Long id) throws Exception {
		roleDao.deleteById(id);

		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());

		return response;
	}
}
