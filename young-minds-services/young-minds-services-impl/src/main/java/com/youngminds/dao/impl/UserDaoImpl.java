package com.youngminds.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	
	@Override
	public boolean validateUser(User user) {
		boolean result = false;
		int count= 0;
		Object params[] = new Object[]{ user.getMobileNo(), user.getPassword()};
        
		String sql = "SELECT count(*) FROM users WHERE mobileNo=? and password=?";
		try{
			count=jdbcTemplate.queryForObject(sql, params, Integer.class);
		}catch(Exception exp){
			System.out.println(exp);
		}
		
		if (count > 0) {
			result  = true;
		}

		return result;
        
	}

	

final class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setMobileNo(new Long(rs.getString("mobileNo")));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}
}
