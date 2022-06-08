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
import com.lawencon.master.dto.chartofaccount.GetAllCoADtoRes;
import com.lawencon.master.dto.chartofaccount.GetByCoAIdDtoRes;
import com.lawencon.master.dto.chartofaccount.GetCoAVoucherTransactionDtoRes;
import com.lawencon.master.dto.chartofaccount.InsertCoADtoReq;
import com.lawencon.master.dto.chartofaccount.UpdateCoADtoReq;
import com.lawencon.master.service.ChartOfAccountService;

@RestController
@RequestMapping("chart-of-accounts")
public class ChartOfAccountController {
	private ChartOfAccountService chartOfAccountService;

	@Autowired
	public ChartOfAccountController(ChartOfAccountService chartOfAccountService) {
		this.chartOfAccountService = chartOfAccountService;
	}

	@PostMapping
	public ResponseEntity<InsertDtoRes> insertData(@RequestBody @Valid InsertCoADtoReq data) throws Exception {
		InsertDtoRes insertData = chartOfAccountService.insert(data);
		return new ResponseEntity<InsertDtoRes>(insertData, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateDtoRes> updateData(@RequestBody @Valid UpdateCoADtoReq data) throws Exception {
		UpdateDtoRes updateData = chartOfAccountService.update(data);
		return new ResponseEntity<UpdateDtoRes>(updateData, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetAllCoADtoRes> getAll() throws Exception {
		GetAllCoADtoRes getAll = chartOfAccountService.getAll();
		return new ResponseEntity<GetAllCoADtoRes>(getAll, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByCoAIdDtoRes> getById(@PathVariable("id") Long id) throws Exception {
		GetByCoAIdDtoRes getById = chartOfAccountService.getById(id);
		return new ResponseEntity<GetByCoAIdDtoRes>(getById, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteDtoRes> deleteById(@PathVariable("id") Long id) throws Exception {
		DeleteDtoRes isSuccessDelete = chartOfAccountService.deleteById(id);
		return new ResponseEntity<DeleteDtoRes>(isSuccessDelete, HttpStatus.OK);
	}

	@GetMapping("/voucher-transaction")
	public ResponseEntity<GetCoAVoucherTransactionDtoRes> getVoucherTransaction() throws Exception {
		GetCoAVoucherTransactionDtoRes getVoucherTransaction = chartOfAccountService.getVoucherTransaction();
		return new ResponseEntity<GetCoAVoucherTransactionDtoRes>(getVoucherTransaction, HttpStatus.OK);
	}
}
