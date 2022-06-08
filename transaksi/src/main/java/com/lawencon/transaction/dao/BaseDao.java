package com.lawencon.transaction.dao;

import java.util.List;

public interface BaseDao<T> {
	
	List<T> getAll() throws Exception;

	T getById(Long id) throws Exception;

	T insert(T data) throws Exception;

	T update(T data) throws Exception;
	
	void deleteById(Long id) throws Exception;

}
