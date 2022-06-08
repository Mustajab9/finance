package com.lawencon.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.auth.constant.ResponseMessageType;
import com.lawencon.auth.dao.RoleDao;
import com.lawencon.auth.dao.UserDao;
import com.lawencon.auth.dto.DeleteDtoRes;
import com.lawencon.auth.dto.InsertDataDtoRes;
import com.lawencon.auth.dto.InsertDtoRes;
import com.lawencon.auth.dto.UpdateDataDtoRes;
import com.lawencon.auth.dto.UpdateDtoRes;
import com.lawencon.auth.dto.user.GetAllUserDtoDataRes;
import com.lawencon.auth.dto.user.GetAllUserDtoRes;
import com.lawencon.auth.dto.user.GetByUserIdDtoDataRes;
import com.lawencon.auth.dto.user.GetByUserIdDtoRes;
import com.lawencon.auth.dto.user.InsertUserDtoReq;
import com.lawencon.auth.dto.user.UpdateUserDtoReq;
import com.lawencon.auth.model.Role;
import com.lawencon.auth.model.User;
import com.lawencon.auth.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private static final long admin = 1L;
	private UserDao userDao;
	private RoleDao roleDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public GetAllUserDtoRes getAll() throws Exception {
		GetAllUserDtoRes getAll = new GetAllUserDtoRes();

		List<User> users = userDao.getAll();
		List<GetAllUserDtoDataRes> listUser = new ArrayList<>();

		int length = users.size();
		for (int i = 0; i < length; i++) {
			User user = users.get(i);
			GetAllUserDtoDataRes data = new GetAllUserDtoDataRes();

			data.setId(user.getId());
			data.setUsername(user.getUsername());
			data.setPassword(user.getPassword());
			data.setRoleName(user.getRoleId().getRoleName());
			data.setVersion(user.getVersion());
			data.setIsActive(user.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}

	@Override
	public GetByUserIdDtoRes getById(Long id) throws Exception {
		GetByUserIdDtoRes getById = new GetByUserIdDtoRes();

		User user = userDao.getById(id);
		GetByUserIdDtoDataRes data = new GetByUserIdDtoDataRes();

		if (user == null) {
			throw new Exception("User With Id " + id + " Not Found ");
		}

		data.setId(user.getId());
		data.setUsername(user.getUsername());
		data.setPassword(user.getPassword());
		data.setRoleName(user.getRoleId().getRoleName());
		data.setVersion(user.getVersion());
		data.setIsActive(user.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertUserDtoReq data) throws Exception {
		User user = new User();

		if (data.getRoleId() == null) {
			throw new Exception("Role Id Cant Null ");
		} else {
			Role role = roleDao.getById(data.getRoleId());

			if (role == null) {
				throw new Exception("Role With Id " + data.getRoleId() + " Not Found ");
			}

			user.setRoleId(role);
		}

		user.setUsername(data.getUsername());

		String password = passwordEncoder.encode(data.getPassword());
		user.setPassword(password);

		user.setCreatedBy(admin);

		User userSave = userDao.insert(user);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(userSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateUserDtoReq data) throws Exception {
		UpdateDtoRes response = new UpdateDtoRes();

		User user = userDao.getById(data.getId());

		user.setUsername(data.getUsername());
		user.setVersion(data.getVersion());

		if (loginId() != null) {
			user.setUpdatedBy(loginId());
		}

		if (data.getIsActive() != null) {
			user.setIsActive(data.getIsActive());
		}

		User userSave = userDao.update(user);

		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(userSave.getVersion());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(Long id) throws Exception {
		userDao.deleteById(id);

		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());

		return response;
	}

	@Override
	public User getByUser(String data) throws Exception {
		User user = userDao.getByUser(data);
		return user;
	}

	@Override
	public Long getUserId() throws Exception {
		return loginId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;

		try {
			user = getByUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Invalid Username Or Password");
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
}
