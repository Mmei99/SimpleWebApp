package com.WebAppFinal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import jakarta.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.WebAppFinal.model.User;



@WebServlet("/ShowUserController")

public class ShowUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		
		Connection con=null;
		
		RequestDispatcher dispatcher=null;
		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webappdemo","root","");
            Statement statement = con.createStatement();
            String sql = "SELECT  user.ID,user.name,user.surname,user.gender,user.date,addresses.workaddress,addresses.homeaddress FROM user,addresses where user.ID="+id+" AND addresses.userID="+id+";";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
				
				System.out.println( resultSet.getString(2));
				System.out.println( resultSet.getString(3));
				System.out.println( resultSet.getString(4));
			
				request.setAttribute("name", resultSet.getString(2));
				request.setAttribute("surname", resultSet.getString(3));
				request.setAttribute("gender", resultSet.getString(4));
				request.setAttribute("date", resultSet.getDate(5));
				request.setAttribute("workaddress", resultSet.getString(6));
				request.setAttribute("homeaddress", resultSet.getString(7));
				RequestDispatcher rd = request.getRequestDispatcher("showUser.jsp");
				rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	
	
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = null;
	
        request.setAttribute("list", list);
        System.out.println(123123123);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
        dispatcher.forward(request, response);
    }

}
