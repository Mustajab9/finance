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
import com.lawencon.transaction.dto.voucher.GetAllVoucherDtoRes;
import com.lawencon.transaction.dto.voucher.GetByVoucherIdDtoRes;
import com.lawencon.transaction.dto.voucher.InsertVoucherDtoReq;
import com.lawencon.transaction.service.VoucherService;

@RestController
@RequestMapping("vouchers")
public class VoucherController {
	private VoucherService voucherService;
	
	@Autowired
	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> insertData(@RequestBody @Valid InsertVoucherDtoReq data) throws Exception{
		InsertDtoRes insertData = voucherService.insert(data);
		return new ResponseEntity<InsertDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllVoucherDtoRes> getAll() throws Exception{
		GetAllVoucherDtoRes getAll = voucherService.getAll();
		return new ResponseEntity<GetAllVoucherDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByVoucherIdDtoRes> getById(@PathVariable("id") Long id) throws Exception{
		GetByVoucherIdDtoRes getById = voucherService.getById(id);
		return new ResponseEntity<GetByVoucherIdDtoRes>(getById, HttpStatus.OK);
	}
}
