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
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends GenericServlet {
	private static final long serialVersionUID = 1L;
   
	 @Override
	    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
	        res.setContentType("text/html");
	        PrintWriter out = res.getWriter();

	        String id=req.getParameter("id");
	        String name = req.getParameter("name");
	        int age =Integer.parseInt( req.getParameter("age"));
	        String course = req.getParameter("course");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "Lakshmi@2003");
	            PreparedStatement ps = con.prepareStatement("UPDATE students SET name=?, age=?, course=? WHERE id=?")){
	            ps.setString(1, name);
	            ps.setLong(2, age);
	            ps.setString(3, course);
	            ps.setString(4, id);

	            int i = ps.executeUpdate();
	            if (i > 0) {
	                out.println("<h2>Student Updated Successfully!</h2>");
	            } else {
	                out.println("<h2>No Student Found with ID: " + id + "</h2>");
	            }
	          }
	        } catch (Exception e) {
	            e.printStackTrace(out);
	        }
	    }


}
