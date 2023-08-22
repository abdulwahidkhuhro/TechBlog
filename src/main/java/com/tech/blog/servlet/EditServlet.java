package com.tech.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;

/**
 * Servlet implementation class EditServlet
 */

@MultipartConfig
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
			
			//fetching all data from file
			
			String userName = request.getParameter("user-name");
			String userEamil = request.getParameter("user-email");
			String userPassword = request.getParameter("user-password");
			String aboutUser = request.getParameter("about-user");
			
			Part part = request.getPart("choose-new-profile-pic");
			
			String newProfilePicName = part.getSubmittedFileName();
			
			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute("currentUser");
			
			user.setName(userName);
			user.setEmail(userEamil);
			user.setPassword(userPassword);
			user.setAbout(aboutUser);
			String oldFileName = user.getProfile();
			user.setProfile(newProfilePicName);
			out.println("Pic name : "+newProfilePicName);
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			
			if(userDao.updateUser(user)) {
				
				String path = request.getRealPath("/")+"pics"+File.separator+user.getProfile();
				
				String oldFilePath = request.getRealPath("/")+"pics"+File.separator+oldFileName;
				
				if(!oldFileName.equals("default.png")) {
					Helper.deleteFile(oldFilePath);
				}
				
				if(Helper.saveFile(part.getInputStream(), path)) {
					out.println("Profile Updated...");
					Message message = new Message("Profile Updated...", "success", "alert-success");
					session.setAttribute("message", message);
					
					
				}else {
					Message message = new Message("Something went wrong...", "error", "alert-danger");
					session.setAttribute("message", message);
				}
			}else {
				
				Message message = new Message("Something went wrong...", "error", "alert-danger");
				session.setAttribute("message", message);
			}
			response.sendRedirect("profile.jsp");
			out.println("</body>");
			out.println("</html>");
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}
