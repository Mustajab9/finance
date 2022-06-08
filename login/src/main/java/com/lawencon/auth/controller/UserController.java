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
import com.lawencon.auth.dto.user.GetAllUserDtoRes;
import com.lawencon.auth.dto.user.GetByUserIdDtoRes;
import com.lawencon.auth.dto.user.InsertUserDtoReq;
import com.lawencon.auth.dto.user.UpdateUserDtoReq;
import com.lawencon.auth.service.UserService;


@RestController
@RequestMapping("users")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> insertData(@RequestBody @Valid InsertUserDtoReq data) throws Exception{
		InsertDtoRes insertData = userService.insert(data);
		return new ResponseEntity<InsertDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateDtoRes> updateData(@RequestBody @Valid UpdateUserDtoReq data) throws Exception{
		UpdateDtoRes updateData = userService.update(data);
		return new ResponseEntity<UpdateDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllUserDtoRes> getAll() throws Exception{
		GetAllUserDtoRes getAll = userService.getAll();
		return new ResponseEntity<GetAllUserDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByUserIdDtoRes> getById(@PathVariable("id") Long id) throws Exception{
		GetByUserIdDtoRes getById = userService.getById(id);
		return new ResponseEntity<GetByUserIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteDtoRes> deleteById(@PathVariable("id") Long id) throws Exception{
		DeleteDtoRes isSuccessDelete = userService.deleteById(id);
		return new ResponseEntity<DeleteDtoRes>(isSuccessDelete, HttpStatus.OK);
	}
	
	@GetMapping("id")
	public ResponseEntity<Long> getId() throws Exception {
		Long authPrincipal = userService.getUserId();
		return new ResponseEntity<Long>(authPrincipal, HttpStatus.OK);
	}
}
