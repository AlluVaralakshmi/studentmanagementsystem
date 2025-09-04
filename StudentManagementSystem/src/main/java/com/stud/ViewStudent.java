package com.stud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ViewStudent
 */
@WebServlet("/ViewStudent")
public class ViewStudent extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
	  @Override
	    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
	        res.setContentType("text/html");
	        PrintWriter out = res.getWriter();

	        out.println("<h2>All Students</h2>");
	        out.println("<table border='1' cellpadding='10'>");
	        out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Course</th></tr>");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "Lakshmi@2003");
	            Statement stmt = con.createStatement()){
	            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getString("id") + "</td>");
	                out.println("<td>" + rs.getString("name") + "</td>");
	                out.println("<td>" + rs.getInt("age") + "</td>");
	                out.println("<td>" + rs.getString("course") + "</td>");
	                out.println("</tr>");
	            }
	            out.println("</table>");
	            
	            }
	        } catch (Exception e) {
	            e.printStackTrace(out);
	        }
	    }

}
