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
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import model.User;

/*
 * Questo servlet viene richiamato tramite il Next Page di variableQuestions.html, serve a prendere le risposte alle domande displayate 
 * dinamicamente e a salvarle, dopodichè reinderezzerà alla parte di statistical question
 * dove sarà possibile tornare indietro (dunque viene richiamato non questo servlet bensì quello antecedente cioè RetrieveVariableQuestions
 * oppure un bottone di submit dove verranno aggiunte anche le statistical question e verranno mandate al db, il quale autonomamente calcolerà
 * anche il puteggio dell'user.
 */

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
		
		for (String x : answers) {
			System.out.println(x);
		}
		request.setAttribute("answers", answers);
		//request.getRequestDispatcher("/PropagationII").include(request, response);
		//String path = ctxpath + "/fixedQuestions.html";
		String path = "/html/fixedQuestions.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		templateEngine.process(path, ctx, response.getWriter());
	}

}
