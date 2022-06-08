package com.lawencon.master.controller;

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

import com.lawencon.master.dto.DeleteDtoRes;
import com.lawencon.master.dto.InsertDtoRes;
import com.lawencon.master.dto.UpdateDtoRes;
import com.lawencon.master.dto.jurnaltype.GetAllTypeDtoRes;
import com.lawencon.master.dto.jurnaltype.GetByTypeIdDtoRes;
import com.lawencon.master.dto.jurnaltype.InsertTypeDtoReq;
import com.lawencon.master.dto.jurnaltype.UpdateTypeDtoReq;
import com.lawencon.master.service.JurnalTypeService;

@RestController
@RequestMapping("types")
public class JurnalTypeController {
	private JurnalTypeService jurnalTypeService;
	
	@Autowired
	public JurnalTypeController(JurnalTypeService jurnalTypeService) {
		this.jurnalTypeService = jurnalTypeService;
	}
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> insertData(@RequestBody @Valid InsertTypeDtoReq data) throws Exception{
		InsertDtoRes insertData = jurnalTypeService.insert(data);
		return new ResponseEntity<InsertDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateDtoRes> updateData(@RequestBody @Valid UpdateTypeDtoReq data) throws Exception{
		UpdateDtoRes updateData = jurnalTypeService.update(data);
		return new ResponseEntity<UpdateDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllTypeDtoRes> getAll() throws Exception{
		GetAllTypeDtoRes getAll = jurnalTypeService.getAll();
		return new ResponseEntity<GetAllTypeDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByTypeIdDtoRes> getById(@PathVariable("id") Long id) throws Exception{
		GetByTypeIdDtoRes getById = jurnalTypeService.getById(id);
		return new ResponseEntity<GetByTypeIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteDtoRes> deleteById(@PathVariable("id") Long id) throws Exception{
		DeleteDtoRes isSuccessDelete = jurnalTypeService.deleteById(id);
		return new ResponseEntity<DeleteDtoRes>(isSuccessDelete, HttpStatus.OK);
	}
}
