package com.WebAppFinal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="registerUserController", urlPatterns={"/registerUserController"})
public class registerUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String gender=request.getParameter("gender");
		String stringdate=request.getParameter("date");
		String homeAddress=request.getParameter("homeAddress");
		String workAddress=request.getParameter("workAddress");
		
		System.out.println(name);
		System.out.println(surname);
		System.out.println(gender);
		System.out.println(stringdate);
		System.out.println(homeAddress);
		System.out.println(workAddress);
		
		
		Connection con=null;
		
		RequestDispatcher dispatcher=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webappdemo","root","");
			PreparedStatement pst=con.prepareStatement("insert into user(name,surname,gender,date) values(?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, surname);
			pst.setString(3, gender);
			pst.setDate(4, java.sql.Date.valueOf(stringdate));
			
			int rowCount=pst.executeUpdate();
			
			
			int id=0;
			Statement st=con.createStatement(  ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from user");
			if(rs.last()){
	               id=rs.getInt("ID");
	           }
			
			PreparedStatement pstadd=con.prepareStatement("insert into addresses(homeaddress,workaddress,userID) values(?,?,?)");
			pstadd.setString(1, homeAddress);
			pstadd.setString(2, workAddress);
			pstadd.setInt(3, id);
			rowCount=rowCount+ pstadd.executeUpdate();
			System.out.println("getServletPath: " + request.getServletPath());
			System.out.println("getServletContext: " + request.getServletContext().getContextPath());
			System.out.println("getServerName: " + request.getServerName());
			System.out.println("getServerPort: " + request.getServerPort());
			
			dispatcher= request.getRequestDispatcher("register.jsp");
			if (rowCount>0) {
				request.setAttribute("status", "success");
				
			}else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
