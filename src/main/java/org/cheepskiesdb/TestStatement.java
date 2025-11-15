package org.cheepskiesdb;
import java.sql.*;

public class TestStatement {
	
  public static void main(String[] args)
      throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
    //Class.forName("com.mysql.jdbc.Driver");
    //System.out.println("Driver loaded");

    // Establish a connection
	  
	  Connection connection = null;
	  
      connection = DriverManager.getConnection
      ("jdbc:mysql://cis3270project.mysql.database.azure.com:3306/cheepskies","cis3270user","Password!");
      System.out.println("Database connected");


	  
    // Create a statement
    Statement statement = connection.createStatement();

    // Execute a statement
    ResultSet resultSet = statement.executeQuery
      ("select first_name, middle_initial, last_name from customers where last_name " + " = 'Doe'");

    // Iterate through the result and print the customer names
    while (resultSet.next())
      System.out.println(resultSet.getString(1) + "\t" +
        resultSet.getString(2) + "\t" + resultSet.getString(3));
    // Close the connection
    connection.close();
  }
}