package com.WebAppFinal.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class User {
	private String name;
	private String surname;
	private String gender;
	private Date date;
	
	private String workAddress;
	private String homeAddress;
	
	
	
	public User (String name,String surname,String gender,Date date,String workAddress,String homeAddress) {
		this.name=name;
		this.surname=surname;
		this.gender=gender;
		this.date=date;
		this.workAddress=workAddress;
		this.homeAddress=homeAddress;
		
	}
	
	
	public User (String name,String surname) {
		this.name=name;
		this.surname=surname;
		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	
     
    
}
    
     
   
