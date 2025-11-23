package org.cheepskiesdb;

import java.sql.*;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnector {

    private static final String url = "jdbc:mysql://cis3270project.mysql.database.azure.com:3306/cheepskies";

    private static final String user = "cis3270user";

    private static final String password = "Password!";

    private Connection connection;

    public static Connection dbConnect() throws SQLException {

        return getConnection(url, user, password);

    }
    public ResultSet executePreparedQuery(String query, List<String> parameters)
            throws SQLException, ClassNotFoundException {

        // Get connection
        Connection conn = dbConnect();

        // Create PreparedStatement
        PreparedStatement pstmt = conn.prepareStatement(query);

        // Set parameters
        for (int i = 0; i < parameters.size(); i++) {
            pstmt.setString(i + 1, parameters.get(i)); // JDBC uses 1-based indexing
        }

        // Execute and return ResultSet
        return pstmt.executeQuery();
    }

}
