package org.cheepskiesdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPreparedStatement {
	
	  public static void main(String[] args)
		      throws SQLException, ClassNotFoundException {
		    // Load the JDBC driver
		    //Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded");

		    // Establish a connection
		    Connection connection = DriverManager.getConnection
		      ("jdbc:mysql://cis3270project.mysql.database.azure.com:3306/cheepskies","cis3270user","Password!");
		    System.out.println("Database connected");

		    // Create a statement
		    PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE last_name = ?;");
		    
		    statement.setString(1, "Doe");

		    // Execute a statement
		    ResultSet resultSet = statement.executeQuery();

		    //statement.executeUpdate()
		    // Iterate through the result and print the student names
		    while (resultSet.next())
		      System.out.println(resultSet.getString(1) + "\t" +
		        resultSet.getString(2) + "\t" + resultSet.getString(3));
		    // Close the connection
		    connection.close();
		  }

}
