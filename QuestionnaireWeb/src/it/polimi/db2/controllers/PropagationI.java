package it.polimi.db2.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;




@WebServlet("/PropagationI")
public class PropagationI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	//private String [] answers = null;
	public PropagationI() {
		super();
	}

	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}
		

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// If the user is not logged in (not present in session) redirect to the login
		String loginpath = getServletContext().getContextPath()+ "/index.html";
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user")== null) {
			response.sendRedirect(loginpath);
			return;
		}
		String[] answers = request.getParameterValues("answer");
		
		//for (String x : answers) {
		//	System.out.println(x);
		//}

		request.setAttribute("answers", answers);
		//System.out.println(request.getAttribute("answers"));
		request.getRequestDispatcher("/PropagationII").include(request, response);
		//RequestDispatcher rd = request.getRequestDispatcher("/PropagationII");
		//rd.forward(request,response);
		//String path = ctxpath + "/fixedQuestions.html";
		
		/*
		System.out.println("sono passato di qui");
		String path = "/html/fixedQuestions.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		templateEngine.process(path, ctx, response.getWriter());
		System.out.println("sono passato di qui");
		*/
	}

}
