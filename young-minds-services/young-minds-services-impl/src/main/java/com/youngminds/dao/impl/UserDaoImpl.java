package com.youngminds.dao.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.youngminds.dao.UserDao;
import com.youngminds.services.modal.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void inserUser(final User user) {
	        
		String sql = "INSERT INTO users VALUES (?,?,?,?)";
		try{
			jdbcTemplate.update(sql, new Object[]{user.getMobileNo(),user.getName(),user.getPassword(),user.getEmail()});
		}catch(Exception exp){
			System.out.println(exp);
		}
		
	}

	
}
