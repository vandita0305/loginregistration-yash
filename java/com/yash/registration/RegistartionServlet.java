package com.yash.registration;

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
 * Servlet implementation class RegistartionServlet
 */
@WebServlet("/register")
public class RegistartionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
//		out.print("CODE IS WORKING");
		String uname = request.getParameter("name");
		String upwd = request.getParameter("pass");
		String uemail = request.getParameter("email");
        String umobile = request.getParameter("contact");
		
		RequestDispatcher dispatcher = null;
		//Connection con=null;
		
//		PrintWriter out = response.getWriter();
//	    out.println(uname);
//	    out.println(uemail);
//	    out.println(umobile);
//	    out.println(upwd);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Root");
			PreparedStatement pst= con.prepareStatement("interst into users(uname,upwd,uemail,umobile)values(?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, upwd);
			pst.setString(3, uemail);
			pst.setString(4, umobile);
			
			int rowCount=pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jps");
			
			if(rowCount>0) {
				request.setAttribute("status", "success");
				
			}else {
				request.setAttribute("status", "failed");
				
			}
			
			dispatcher.forward(request,response);
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
	}
//			finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
}
}


