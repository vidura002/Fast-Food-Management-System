 package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.DBConnection;


@WebServlet("/log")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uname = request.getParameter("usersUid");
		String pwd = request.getParameter("usersPwd");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE uname = ? AND pwd = ?");
			pst.setString(1, uname);
			pst.setString(2, pwd);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				
				session.setAttribute("name",rs.getString("uname"));
				dispatcher = request.getRequestDispatcher("menu.jsp");
			}else {
				request.setAttribute("status", "faild");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
