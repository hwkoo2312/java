package com.jsp.user.service;

import java.sql.SQLException;

import com.jsp.jdbc.connection.ConnectionProvider;
import com.jsp.user.User;
import com.jsp.user.dao.UserDao;

public class UserService {

	private static UserService userService = new UserService();
	
	private UserService(){
		
	}
	
	public static UserService getInstance(){
		return userService;
		
	}
	
	public int insert(User user) throws SQLException{
		UserDao userDao = UserDao.getInstance();
		return userDao.insert(ConnectionProvider.getConnection(), user);
	}
}
