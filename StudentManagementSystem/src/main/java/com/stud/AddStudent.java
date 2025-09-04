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
 *CREATE TABLE `studentmanagement`.`students` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `age` INT NULL,
  `course` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

 */
@WebServlet("/AddStudent")
public class AddStudent extends GenericServlet {
	private static final long serialVersionUID = 1L;
    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String course = req.getParameter("course");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "Lakshmi@2003");
            PreparedStatement ps = con.prepareStatement("INSERT INTO students(id,name,age,course) VALUES(?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setLong(3, age);
            ps.setString(4, course);
          

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h2>Student Added Successfully!</h2>");
            } else {
                out.println("<h2>Failed to Add Student!</h2>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }


}
