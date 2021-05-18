package com.userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.userInfoDb.UserInfoDbConnector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A real estate client would like you to create an application which has an accessible end point that can 
 * * receive an HTTP POST request, 
 * * ingest the form data, 
 * perform reasonable sanitization of the data, 
 * safely store the data in a database, 
 * return a response state with some indication of what transpired
 * @author macsh
 *
 */
public class SaveUserInfoApp extends HttpServlet{
	private UserInfoDbConnector dbConnect;
	private static final long serialVersionUID = 1L;
	/**
	 * Validates user info from form
	 * @param name
	 * @param email
	 * @param address
	 * @param phone
	 * @param budget
	 * @return
	 */
	public boolean isUserInfoValid(String name, String email, String address, String phone, String budget) {
		if(name==null||name.isBlank()||email==null||email.isBlank()||address==null||address.isBlank()||phone==null||phone.isBlank()||budget==null||budget.isBlank())
			return false;
		boolean nameValid=Pattern.matches("^[a-zA-Z ]+$", name);
		boolean emailValid=Pattern.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", email);
		boolean addressValid=Pattern.matches("^[a-zA-Z0-9 ]+$", address);
		boolean phoneValid=Pattern.matches("^[0-9-]+$", phone);
		boolean budgetValid=Pattern.matches("^[a-zA-Z0-9- ]+$", budget);
		return nameValid&&emailValid&&addressValid&&phoneValid&&budgetValid;
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("userName");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String budget = req.getParameter("budget");
		
		//after checking user input validity, insert new user into database if it exists
		String postResult="";
		if(this.isUserInfoValid(name,email,address,phone,budget)) {
			
			if(this.dbConnect==null) {
				postResult="ERROR: DB DNE";
			}
			else {
				try {
					postResult=this.dbConnect.insertUserInfo(name, email, address, phone, budget);
				} catch (SQLException e) {
					e.printStackTrace();
					postResult=e.getMessage();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					postResult=e.getMessage();
				}
			}			
		}
		else {
			postResult="User input invalid.";
		}
		
		req.setAttribute("result", postResult);
		RequestDispatcher view = req.getRequestDispatcher("userInfo.jsp");
		try {
			view.include(req, res);
			view.forward(req, res);
		} catch (ServletException | IOException e1) {
			e1.printStackTrace();
			PrintWriter out = res.getWriter();
			out.println("There was a server error.");
			out.println(postResult);
		}
	}
	
	@Override
	public void init() {
		System.out.println("Servlet " + this.getServletName() + " has started");
		this.dbConnect= new UserInfoDbConnector();
		try {
			this.dbConnect.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error during DB connection.");
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
}
