package com.WebAppFinal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.WebAppFinal.Dao.UserDao;
import com.WebAppFinal.model.User;
@WebServlet ("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDAO;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    
    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = null;
	
        request.setAttribute("list", list);
        System.out.println(123123123);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
        dispatcher.forward(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		 try {
		        switch (action) {
		        case "/new":
		            showNewForm(request, response);
		            break;
		        case "/insert":
		            insertBook(request, response);
		            break;
		        case "/delete":
		            deleteUser(request, response);
		            break;
		        case "/edit":
		            showEditForm(request, response);
		            break;
		        case "/update":
		            updateBook(request, response);
		            break;
		        default:
		            listBook(request, response);
		            break;
		        }
		    } catch (SQLException ex) {
		        throw new ServletException(ex);
		    }
		
		
		
		
		
	}
	
	
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
	
	private void showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id=Integer.parseInt(request.getParameter("UserList.jsp"));
		User existingUser;
	
        existingUser=userDAO.getUser(id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("UserList.jsp");
        request.setAttribute("User",existingUser);
        
        dispatcher.forward(request, response);
    }

}


