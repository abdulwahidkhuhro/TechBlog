package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.User;
import com.tech.blog.entities.Message;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try(PrintWriter out = response.getWriter()){
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Title</title>");
			out.println("</head>");
			out.println("<body>");
			String userEmail = request.getParameter("email");
			String userPassword = request.getParameter("password");
			
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			
			User user = userDao.getUserByEmailAndPassword(userEmail, userPassword);
			
			if(user == null) {
				//login failure
//				out.println("Invalid Details try again...");
				Message message = new Message("Invalid Details ! try with another","error","alert-danger");
				HttpSession session = request.getSession();
				session.setAttribute("message", message);
				response.sendRedirect("login_page.jsp");
			}else {
				//login success
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", user);
				response.sendRedirect("profile.jsp");
			}
			out.println("</body>");
			out.println("</html>");
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}

}
