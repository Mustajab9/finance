package com.lawencon.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.auth.dto.DeleteDtoRes;
import com.lawencon.auth.dto.InsertDtoRes;
import com.lawencon.auth.dto.UpdateDtoRes;
import com.lawencon.auth.dto.role.GetAllRoleDtoRes;
import com.lawencon.auth.dto.role.GetByRoleIdDtoRes;
import com.lawencon.auth.dto.role.InsertRoleDtoReq;
import com.lawencon.auth.dto.role.UpdateRoleDtoReq;
import com.lawencon.auth.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {
	private RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> insertData(@RequestBody @Valid InsertRoleDtoReq data) throws Exception{
		InsertDtoRes insertData = roleService.insert(data);
		return new ResponseEntity<InsertDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateDtoRes> updateData(@RequestBody @Valid UpdateRoleDtoReq data) throws Exception{
		UpdateDtoRes updateData = roleService.update(data);
		return new ResponseEntity<UpdateDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllRoleDtoRes> getAll() throws Exception{
		GetAllRoleDtoRes getAll = roleService.getAll();
		return new ResponseEntity<GetAllRoleDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByRoleIdDtoRes> getById(@PathVariable("id") Long id) throws Exception{
		GetByRoleIdDtoRes getById = roleService.getById(id);
		return new ResponseEntity<GetByRoleIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteDtoRes> deleteById(@PathVariable("id") Long id) throws Exception{
		DeleteDtoRes isSuccessDelete = roleService.deleteById(id);
		return new ResponseEntity<DeleteDtoRes>(isSuccessDelete, HttpStatus.OK);
	}
}
