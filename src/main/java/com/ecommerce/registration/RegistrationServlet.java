package com.ecommerce.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String  user_Name = request.getParameter("name");
		String user_pass = request.getParameter("pass");
		String  user_email = request.getParameter("email");
		String  user_Mobile = request.getParameter("contact");
		
			RequestDispatcher dispatcher = null;
			Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ecommerce?useSSL=false","root","Nilesh@1998");
			PreparedStatement pst = con.prepareStatement("insert into users (user_Name,user_pass,user_email,user_Mobile) values(?,?,?,?)");
			pst.setString(1, user_Name);
			pst.setString(2, user_pass);
			pst.setString(3, user_email);
			pst.setString(4, user_Mobile);
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount > 0) {
				request.setAttribute("status","success");
			}else
			{
				request.setAttribute("status","failed");
			}
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
