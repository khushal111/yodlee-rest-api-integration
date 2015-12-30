package com.yarishllc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MySQLJDBC2 {

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/yarish";

  static final String USER_NAME = "root";
  static final String PASSWORD = "root";

  public static void main(String[] args) {
    System.out.println("JDBC example!");
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      // Step 2 - register and load the driver
      Class.forName(JDBC_DRIVER);

      // step 3 : open connection
      System.out.println("Connecting to database");
      connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

      // /STEP 4: Execute a query
      System.out.println("Creating statement...");
      String sql = "UPDATE Employees set age=? WHERE id=?";
      statement = connection.prepareStatement(sql);
      statement.setInt(1, 35);
      statement.setInt(2, 102);
      int rows = statement.executeUpdate();
      System.out.println("no of rows affected = " + rows);

      statement.close();
      connection.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (statement != null)
          statement.close();
      } catch (SQLException se2) {
      }// nothing we can do
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }

  }
}
