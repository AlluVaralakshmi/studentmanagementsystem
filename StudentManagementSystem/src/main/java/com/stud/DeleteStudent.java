package com.stud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class DeleteStudent
 */
@WebServlet("/DeleteStudent")
public class DeleteStudent extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
	  @Override
	    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
	        res.setContentType("text/html");
	        PrintWriter out = res.getWriter();

	        String id = req.getParameter("id");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "Lakshmi@2003");
	            PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?")){
	            ps.setString(1, id);

	            int i = ps.executeUpdate();
	            if (i > 0) {
	                out.println("<h2>Student Deleted Successfully!</h2>");
	            } else {
	                out.println("<h2>No Student Found with ID: " + id + "</h2>");
	            }
	           
	        }
	        }catch (Exception e) {
	            e.printStackTrace(out);
	        }
	        
	    }

}
