package com.tech.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
maxFileSize=1024*1024*50,      	// 50 MB
maxRequestSize=1024*1024*100)
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	try{
    		
    		out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Title</title>");
			out.println("</head>");
			out.println("<body>");
			
			
			
    		int id = Integer.parseInt(request.getParameter(""));
    		String title = request.getParameter("post-title");
    		String content = request.getParameter("post-content");
    		String code = request.getParameter("post-code");
    		Part part = request.getPart("post-image");
    		
    		out.println("<h1> "+part.getSubmittedFileName()+" </h1>");
    		out.println("File name : "+part.getSubmittedFileName());
    		
    		out.println("</body>");
			out.println("</html>");
    	}catch(Exception exception) {
    		
    	}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		processRequest(request, response);
		
		response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	try{
    		
    		out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Title</title>");
			out.println("</head>");
			out.println("<body>");
			
			
			
    		int id = Integer.parseInt(request.getParameter(""));
    		String title = request.getParameter("post-title");
    		String content = request.getParameter("post-content");
    		String code = request.getParameter("post-code");
    		Part part = request.getPart("post-image");
    		
    		out.println("<h1> "+part.getSubmittedFileName()+" </h1>");
    		out.println("File name : "+part.getSubmittedFileName());
    		
    		out.println("</body>");
			out.println("</html>");
    	}catch(Exception exception) {
    		
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
//		Part part = request.getPart("post-image");
//		request.setAttribute("demo", part.getSubmittedFileName());
//		response.sendRedirect("demo.jsp");
	}

}
