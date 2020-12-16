package it.polimi.db2.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UsernameException;
import model.User;
import services.UserService;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/html/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB(name="services/UserService")   
	UserService userService;
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		System.out.println(username);
		String email=request.getParameter("email");
		System.out.println(email);
		String password=request.getParameter("password");
		System.out.println(password);
		try {
			userService.createUser(username,password,email);
		} catch (UsernameException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to create the account. "+e.getMessage());
			return;
		}
		String ctxpath = getServletContext().getContextPath();
		String path = ctxpath + "/Home";
		response.sendRedirect(path);
	}

}
