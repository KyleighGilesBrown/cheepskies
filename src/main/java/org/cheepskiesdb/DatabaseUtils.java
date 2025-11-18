package org.cheepskiesdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtils {

    public static boolean usernameScan(String username) {
        String query = "SELECT 1 FROM credentials WHERE username = ? LIMIT 1";

        try (Connection conn = DatabaseConnector.dbConnect(); PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, username);

            // this creates an object of the resulting SQL query table
            ResultSet rs = statement.executeQuery();

            // SQL queries set the cursor the row BEFORE resulting table, rs.next() moves the cursor to next row
            // return rs.next(); says, does the first row have a value? If it does, return true.
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean emailScan(String email) {
        String query = "SELECT 1 FROM customers WHERE email = ? LIMIT 1";

        try (Connection conn = DatabaseConnector.dbConnect(); PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, email);

            // this creates an object of the resulting SQL query table
            ResultSet rs = statement.executeQuery();

            // SQL queries set the cursor the row BEFORE resulting table, rs.next() moves the cursor to next row
            // return rs.next(); says, does the first row have a value? If it does, return true.
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean loginValidation(String username, String password) {
        String query = "SELECT password FROM credentials WHERE username = ?";

        try (Connection conn = DatabaseConnector.dbConnect(); PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, username);

            // this creates an object of the resulting SQL query table
            ResultSet rs = statement.executeQuery();

            // SQL queries set the cursor the row BEFORE resulting table, rs.next() moves the cursor to next row
            // this is different, if !rs.next specifies, if the next row does NOT exist, execute if statement
            // in that instance it will false
            if (!rs.next()) {
                return false;
            }

            // looks at the table, which is only one row as username is a unique key
            // it then grabs the String value of the password table

            String pass = rs.getString("password");

            // if the password in the table is equal to the password parameter, return true
            return pass.equals(password);

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }


}
