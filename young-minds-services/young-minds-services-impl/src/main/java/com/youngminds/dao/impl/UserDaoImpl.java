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
	public User validateUser(User user) {
		User resultUser= null;
		Object params[] = new Object[]{ user.getMobileNo().toString(), user.getPassword()};
        
		String sql = "SELECT * FROM users WHERE mobile_no=? and password=?";
		try{
			resultUser=jdbcTemplate.queryForObject(sql, new UserMapper(), params);
		}catch(Exception exp){
			System.out.println(exp);
		}
		
		return resultUser;
        
	}

	

final class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setMobileNo(new Long(rs.getString("mobile_no")));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}
}
