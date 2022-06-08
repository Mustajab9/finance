package com.lawencon.transaction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.transaction.dto.InsertDtoRes;
import com.lawencon.transaction.dto.gl.GetAllGLDtoRes;
import com.lawencon.transaction.dto.gl.GetByGLIdDtoRes;
import com.lawencon.transaction.dto.gl.InsertGLDtoReq;
import com.lawencon.transaction.service.GeneralLedgerService;

@RestController
@RequestMapping("general-ledgers")
public class GeneralLedgerController {
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	public GeneralLedgerController(GeneralLedgerService generalLedgerService) {
		this.generalLedgerService = generalLedgerService;
	}
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> insertData(@RequestBody @Valid InsertGLDtoReq data) throws Exception{
		InsertDtoRes insertData = generalLedgerService.insert(data);
		return new ResponseEntity<InsertDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllGLDtoRes> getAll() throws Exception{
		GetAllGLDtoRes getAll = generalLedgerService.getAll();
		return new ResponseEntity<GetAllGLDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByGLIdDtoRes> getById(@PathVariable("id") Long id) throws Exception{
		GetByGLIdDtoRes getById = generalLedgerService.getById(id);
		return new ResponseEntity<GetByGLIdDtoRes>(getById, HttpStatus.OK);
	}
}
