package com.lawencon.auth.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.auth.dao.UserDao;
import com.lawencon.auth.model.Role;
import com.lawencon.auth.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
	@Override
	public List<User> getAll() throws Exception {
		List<User> data = em.createQuery("FROM User", User.class).getResultList();
		
		return data;
	}
	
	@Override
	public User getById(Long id) throws Exception {
		return super.getById(User.class, id);
	}
	
	@Override
	public User insert(User data) throws Exception {
		return super.saveInsert(data);
	}
	
	@Override
	public User update(User data) throws Exception {
		return super.saveUpdate(data);
	}
	
	
	@Override
	public void deleteById(Long id) throws Exception {
		super.deleteById(User.class, id);
	}
	
	@Override
	public User getByUser(String username) {
		String sql = "SELECT u.id AS user_id, u.username, u.\"password\","
				+ " r.id AS role_id, r.role_code, u.version, u.is_Active"
				+ " FROM users AS u"
				+ " INNER JOIN user_role AS r ON r.id = u.role_id"
				+ " WHERE u.username = :username";
		
		User data = null;
		try {
			Object result = em.createNativeQuery(sql)
					.setParameter("username", username)
					.getSingleResult();
			
			Object[] obj = (Object[]) result;
			data = new User();
			data.setId(Long.valueOf(obj[0].toString()));
			data.setUsername(obj[1].toString());
			data.setPassword(obj[2].toString());
			
			Role role = new Role();
			role.setId(Long.valueOf(obj[3].toString()));
			role.setRoleCode(obj[4].toString());
			data.setRoleId(role);
			
			data.setVersion(Integer.valueOf(obj[5].toString()));
			data.setIsActive(Boolean.valueOf(obj[6].toString()));
		
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
