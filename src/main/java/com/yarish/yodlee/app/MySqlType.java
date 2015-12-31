package com.yarish.yodlee.app;

public class MySqlType {

  // insert date
  // http://stackoverflow.com/questions/9907210/mysql-date-formats-difficulty-inserting-a-date

  // insert datetime
  // http://stackoverflow.com/questions/10636152/mysql-how-to-parse-a-string-value-to-datetime-format-inside-an-insert-statemen

  // SELECT * FROM yodleedata.MySqlType;
  //
  // insert into MySqlType (id,dob,dobtime,price,name) values (1,null,null,100.00,'yarish');
  //
  // insert into MySqlType (id,dob,dobtime,price,name) values (2,STR_TO_DATE('1-01-2012',
  // '%d-%m-%Y'),null,100.00,'yarish');
  //
  // insert into MySqlType (id,dob,dobtime,price,name) values (3,STR_TO_DATE('1-01-2012',
  // '%d-%m-%Y'),STR_TO_DATE('1-01-2012 8:06:26 AM', '%d-%m-%Y %r'),100.00,'yarish');
  //
  // insert into MySqlType (id,dob,dobtime,price,name) values (5,STR_TO_DATE('31-12-2015',
  // '%d-%m-%Y'),STR_TO_DATE('31-12-2015 8:06:26 AM', '%d-%m-%Y %r'),100.30,'yarish');
  //
  // commit;

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  // static final String DB_URL = "jdbc:mysql://localhost/yarish";
  static final String DB_URL =
      "jdbc:mysql://dev-db-instance1-encrypted.cuwneoknj5z2.us-west-2.rds.amazonaws.com/yodleedata";


  static final String USER_NAME = "dbuser1";
  static final String PASSWORD = "J&4!6pqm55IW";

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
