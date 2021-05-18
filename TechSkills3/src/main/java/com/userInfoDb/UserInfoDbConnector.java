package com.userInfoDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfoDbConnector {
   private String dbUrl="jdbc:mysql://127.0.0.1:3306/userinfo";
   private String dbUserName="root";
   private String dbPassword="traveler";
	   
   public void createDB() throws ClassNotFoundException, SQLException {
	   Connection conn = this.getConnection();
	   Statement stmt = conn.createStatement();
	   String sql = "CREATE DATABASE userInfoDb";
	   stmt.executeUpdate(sql);
	   System.out.println("Database created successfully...");   	
	   stmt.close();
	   conn.close();
   }
   public Connection getConnection() throws ClassNotFoundException, SQLException {
	   String dbDriver = "com.mysql.cj.jdbc.Driver";
	   Class.forName(dbDriver);
	   System.out.println("Connecting to DB.");
	   Connection conn = DriverManager.getConnection(this.dbUrl, this.dbUserName, this.dbPassword);
	   System.out.println(conn.getMetaData());
	   return conn;
   }
   public boolean doesUserExist(String name, Connection conn) throws SQLException {
	   String sql =  "SELECT * FROM userInfoDb WHERE name=?;";
	   PreparedStatement stmt = conn.prepareStatement(sql);
	   stmt.setString(1, name);
       ResultSet user=stmt.executeQuery();
       boolean userExists=false;
       if(user!=null && user.next())
    	   userExists=true;
       stmt.close();
	return userExists;
	   
   }
   /**
    * 
    * @return
    * @throws SQLException
 * @throws ClassNotFoundException 
    */
   public String insertUserInfo(String name, String email, String addr, String phone, String budget) throws SQLException, ClassNotFoundException {
	   Connection conn = this.getConnection();
	   if(this.doesUserExist(name, conn)) {
		   System.out.println("User already exists.");
		   return "User already exists.";
	   }
	   else {
		   System.out.println("Inserting records into the table...");          
		   String sql =  "INSERT INTO userInfoDb VALUES (?,?,?,?,?);";
		   PreparedStatement stmt = conn.prepareStatement(sql);
		   stmt.setString(1,name);
		   stmt.setString(2,email);
		   stmt.setString(3,addr);
		   stmt.setString(4,phone);
		   stmt.setString(5,budget);
	       int numRow=stmt.executeUpdate();
	       stmt.close();
	       conn.close();
	       System.out.println("Insert complete.");
	       return "Insert complete.";
	   }     
   }
}
