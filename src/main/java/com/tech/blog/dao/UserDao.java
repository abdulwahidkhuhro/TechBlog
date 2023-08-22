package com.tech.blog.dao;

import java.sql.*;

import com.tech.blog.entities.User;

public class UserDao {
	private Connection connection;
	
	public UserDao(Connection connection) {
		this.connection = connection;
	}
	
	//method to insert user to database,
	
	public boolean saveUser( User user) {
		boolean isSaved = false;
		try {
			// user ---> database
			
			String query = "insert into user (name, email, password, gender, about) values (?,?,?,?,?);";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getGender());
			preparedStatement.setString(5, user.getAbout());
			
			
			preparedStatement.executeUpdate();
			isSaved = true;
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return isSaved;
	}
	
	public boolean updateUser(User user) {
		boolean isUpdated = false;
		try {
			String query = "update user set name=? , email=? , password=? , gender=? , about=? , profile=? where id=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getGender());
			preparedStatement.setString(5, user.getAbout());
			preparedStatement.setString(6, user.getProfile());
			
			preparedStatement.setInt(7, user.getId());
			
			preparedStatement.executeUpdate();
			
			isUpdated = true;
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return isUpdated;
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		
		User user = null;
		try {
			String query = "select * from user where email = ? and password = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setGender(resultSet.getString("gender"));
				user.setAbout(resultSet.getString("about"));
				user.setRegistrationTime(resultSet.getTimestamp("rdate"));
				user.setProfile(resultSet.getString("profile"));
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return user;
	}
}
