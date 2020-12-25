package it.polimi.db2.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@WebServlet("/PropagationII")
public class PropagationII extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	private String [] answers_prop;

    public PropagationII() {
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono entrato nel doGet poichè richiamato");
		answers_prop = (String[]) request.getAttribute("answers");
		
		for ( Object x : answers_prop) {
			System.out.println(x);
		}
		
		/*		
		String path = "/html/greetings.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		templateEngine.process(path, ctx, response.getWriter());
		*/
				
		String path = "/html/fixedQuestions.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		templateEngine.process(path, ctx, response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono entrato in doPost poichè ho cliccato il submit button");
		String gender = null;
		String age = null;
		String exp_lvl = null;
		gender = request.getParameter("gender");
		age = request.getParameter("age");
		exp_lvl = request.getParameter("expertise");
		System.out.println(gender);
		System.out.println(age);
		System.out.println(exp_lvl);
		//Adesso che l'utente ha inserito tutte le informazioni posso inserire le risposte recuperate in PropagationI e le risposte alle statistical question
		
		
		}
		
		
		
	}


