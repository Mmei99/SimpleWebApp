package com.WebAppFinal.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.WebAppFinal.model.User;
 
/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class UserDao {
    private String jdbcURL="jdbc:mysql://localhost:3306/webappdemo";
    private String jdbcUsername="root";
    private String jdbcPassword="";
    private Connection jdbcConnection;
    
    public UserDao() {
	}
    
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    
     
    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
         
        String sql = "SELECT * FROM user";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
           
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
           
            System.out.println(123123123);
            User user = new User(name, surname);
            listUser.add(user);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listUser;
    }
     
    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user where user.ID = ?";
        boolean rowDeleted ;
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        rowDeleted = statement.executeUpdate() > 0;
        
        disconnect();
        return rowDeleted;     
    }
     
   
     
    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM book WHERE ID = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String gender = resultSet.getString("gender");
            Date date = resultSet.getDate("date");
            String workaddress = resultSet.getString("surname");
            String homeaddress = resultSet.getString("surname");
             
            user = new User(name, surname, gender, date,workaddress,homeaddress);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }

    
}