package com.tech.blog.helper;
import java.sql.*;

public class ConnectionProvider {
	
	private static Connection connection;
	public ConnectionProvider() {
		System.out.println("Hello World");
	}
	public static Connection getConnection() {
		
		try {
			
			if(connection == null) {
				//driver class load
				
				Class.forName("com.mysql.jdbc.Driver");
				
				
				//create a connection
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techblog","root","4244");
				System.out.println("Inside ConnectionProvider class : "+connection);
				
			}
		}catch(Exception exception) {
			System.out.println("catch : "+exception.getMessage());
			exception.printStackTrace();
		}
		return connection;
	}
	
	
}
