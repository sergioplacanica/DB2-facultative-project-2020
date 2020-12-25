package it.polimi.db2.controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import model.User;

//import model.User;

@WebServlet("/Home")
public class HomePageHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;

	public HomePageHandler() {
		super();
	}

	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		//templateResolver.setPrefix("WEB-INF/");
		templateResolver.setSuffix(".html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*String resourcePath = getServletContext().getContextPath() + "/index.html";
		System.err.println(resourcePath);
		HttpSession session = req.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			res.sendRedirect(resourcePath);
			return;
		}*/
		// If the user is not logged in (not present in session) redirect to the login
		String loginpath = getServletContext().getContextPath()+ "/index.html";
		HttpSession session = request.getSession();
		User username = (User) session.getAttribute("user");
		System.out.println(username.getUsername());
		//nel caso tu non riesca ad accedere ai dati di session basta che setti una variabile
		//tramite ctx.setVariable("username",username.getUsername())
		if (session.isNew() || session.getAttribute("user")== null) {
			response.sendRedirect(loginpath);
			return;
		}
		String path = "html/homepage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		//if the run stops here you need to add ognl library
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}
}
