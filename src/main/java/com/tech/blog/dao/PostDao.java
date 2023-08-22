package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tech.blog.entities.Category;
public class PostDao {
	private Connection connection;
	
	
	public PostDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> list = new ArrayList<>();
		
		try {
			String query = "select * from categories;";
			
			Statement statement =  this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				
				int categoryID = resultSet.getInt("category_id");
				String categoryName = resultSet.getString("category_name");
				String categoryDescription = resultSet.getString("category_description");
				
				list.add(new Category(categoryID, categoryName, categoryDescription));
			}
			
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		
		
		return list;
	}
}
