package it.polimi.db2.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import exceptions.CredentialsException;
import exceptions.NonUniqueResultException;
import model.User;

import javax.ejb.EJB;
import services.UserService;
import javax.servlet.ServletContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.apache.commons.lang3.StringEscapeUtils;



/**
 * Servlet implementation class CheckLogin
 */
//@SuppressWarnings("deprecation")
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private TemplateEngine templateEngine;
	@EJB(name = "services/UserService")
	UserService usrService;
       
    /*public CheckLogin() {
        super();
    }
        
    public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}*/
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usrn = null;
		String pwd = null;
		try {
			usrn = request.getParameter("username");
			pwd = request.getParameter("pwd");
			System.out.println(usrn + pwd);
			if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty()) {
				throw new Exception("Missing or empty credential value");
			}

		} catch (Exception e) {
			//for debugging only e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
			return;
		}
		User user;
		try {
			 //query db to authenticate for user
			user = usrService.checkCredentials(usrn, pwd);
		} catch (CredentialsException | NonUniqueResultException e) {
			e.printStackTrace();
			//TODO: handle exception 500 bad request
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
			return;
		}
		// If the user exists, add info to the session and go to home page, otherwise
		// show login page with error message
		System.out.println(user);
		String path;
		if (user == null) {
			//TODO: handle error message in the index.html
			path = getServletContext().getContextPath() + "/index.html";
			response.sendRedirect(path);
		} else {		
			request.getSession().setAttribute("user", user);
			path = getServletContext().getContextPath() + "/html/homepage.html";
			response.sendRedirect(path);
		} 
		
	}
	
	public void destroy() {
	}

}
