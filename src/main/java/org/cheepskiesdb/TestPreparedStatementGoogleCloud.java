package org.cheepskiesdb;

import java.sql.*;

public class TestPreparedStatementGoogleCloud {
	
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
		    PreparedStatement statement = connection.prepareStatement("select firstName, mi, lastName from Student where lastName "
		        + " = ?");
		    
		    statement.setString(1, "Smith");

		    // Execute a statement
		    ResultSet resultSet = statement.executeQuery();

		    // Iterate through the result and print the student names
		    while (resultSet.next())
		      System.out.println(resultSet.getString(1) + "\t" +
		        resultSet.getString(2) + "\t" + resultSet.getString(3));
		    // Close the connection
		    connection.close();
		  }

}
