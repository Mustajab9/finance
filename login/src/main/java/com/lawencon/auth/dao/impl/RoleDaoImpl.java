package com.lawencon.auth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.auth.dao.RoleDao;
import com.lawencon.auth.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {
	
	@Override
	public List<Role> getAll() throws Exception {
		List<Role> data = em.createQuery("FROM Role", Role.class).getResultList();
		
		return data;
	}
	
	@Override
	public Role getById(Long id) throws Exception {
		return super.getById(Role.class, id);
	}
	
	@Override
	public Role insert(Role data) throws Exception {
		return super.saveInsert(data);
	}
	
	@Override
	public Role update(Role data) throws Exception {
		return super.saveUpdate(data);
	}
	
	@Override
	public void deleteById(Long id) throws Exception {
		super.deleteById(Role.class, id);
	}
}
