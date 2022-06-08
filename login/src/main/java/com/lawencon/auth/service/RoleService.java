package com.lawencon.auth.service;

import com.lawencon.auth.dto.InsertDtoRes;
import com.lawencon.auth.dto.UpdateDtoRes;
import com.lawencon.auth.dto.role.GetAllRoleDtoRes;
import com.lawencon.auth.dto.role.GetByRoleIdDtoRes;
import com.lawencon.auth.dto.role.InsertRoleDtoReq;
import com.lawencon.auth.dto.role.UpdateRoleDtoReq;

public interface RoleService extends BaseSevice {
	GetAllRoleDtoRes getAll() throws Exception;

	GetByRoleIdDtoRes getById(Long id) throws Exception;

	InsertDtoRes insert(InsertRoleDtoReq data) throws Exception;
	
	UpdateDtoRes update(UpdateRoleDtoReq data) throws Exception;
}
