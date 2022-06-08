package com.lawencon.auth.dao;

import com.lawencon.auth.model.User;

public interface UserDao extends BaseDao<User> {
	User getByUser(String username) throws Exception;
}
