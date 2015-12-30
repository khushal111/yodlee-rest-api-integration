package com.yarish.yodlee.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CobrandDB {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  // static final String DB_URL = "jdbc:mysql://localhost/yarish";
  static final String DB_URL =
      "jdbc:mysql://dev-db-instance1-encrypted.cuwneoknj5z2.us-west-2.rds.amazonaws.com/yodleedata";


  static final String USER_NAME = "dbuser1";
  static final String PASSWORD = "J&4!6pqm55IW";

  public static void main(String[] args) {
    System.out.println("CobrandDB!");
    Connection connection = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;

    try {
      // Step 2 - register and load the driver
      Class.forName(JDBC_DRIVER);

      // step 3 : open connection
      System.out.println("Connecting to database");
      connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);


      // /STEP 4: Execute a query
      System.out.println("Creating statement...");

      String username = "sbCobstackup";
      String password = "74bea3d6-a3b1-4681-8cfd-d2a6b3b9a66a";
      String encrypted_password = "D8/Tfp3ShsxDCdWLoTopPZ2hTCjgMZzfQjdXesHTS2vT5gTME6qdKdrY05YSWtPI";
      String secretKey = "06fdec84346edff41105b4813fae4cca";

      String sql = "insert into Cobrand (username,password,encrypted_password) values (?,?,?)";
      prepStatement = connection.prepareStatement(sql);
      prepStatement.setString(1, username);
      prepStatement.setString(2, password);
      prepStatement.setString(3, encrypted_password);
      int rowsAffectd = prepStatement.executeUpdate();
      System.out.println("rowsAffectd=" + rowsAffectd);

      sql = "select username , password from Cobrand";


      // step 6 : cleanup
      prepStatement.close();
      connection.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (prepStatement != null)
          prepStatement.close();
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
