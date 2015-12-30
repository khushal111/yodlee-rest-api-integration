package com.yarishllc.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MySQLJDBC {

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  // static final String DB_URL = "jdbc:mysql://localhost/yarish";
  static final String DB_URL =
      "jdbc:mysql://dev-db-instance1-encrypted.cuwneoknj5z2.us-west-2.rds.amazonaws.com/yodleedata";


  static final String USER_NAME = "dbuser1";
  static final String PASSWORD = "J&4!6pqm55IW";

  public static void main(String[] args) {
    System.out.println("JDBC example!");
    Connection connection = null;
    java.sql.Statement statement = null;

    try {
      // Step 2 - register and load the driver
      Class.forName(JDBC_DRIVER);

      // step 3 : open connection
      System.out.println("Connecting to database");
      connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);


      // /STEP 4: Execute a query
      System.out.println("Creating statement...");
      // statement = connection.createStatement();
      statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      // String sql = "SELECT id, first, last, age FROM Employees";
      String sql = "SELECT UserID,EmailAddress FROM User";


      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        // Retrieve by column name
        int userid = resultSet.getInt("UserID");
        String emailAddress = resultSet.getString("EmailAddress");

        // Display values
        System.out.println("userid: " + userid);
        System.out.println("emailAddress: " + emailAddress);
      }

      // step 6 : cleanup
      resultSet.close();
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
