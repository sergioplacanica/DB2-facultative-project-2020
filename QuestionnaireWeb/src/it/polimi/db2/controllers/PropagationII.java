package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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

import exceptions.AnswerException;
import model.Answer;
import model.Marketingquestion;
import model.Product;
import model.Questionnaire;
import model.User;
import services.AnswerService;
import services.QuestionnaireService;
import services.UserService;

@WebServlet("/PropagationII")
public class PropagationII extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	private String[] answers_prop;
	@EJB(name = "services/QuestionnaireService")
	QuestionnaireService qService;

	@EJB(name = "services/AnswerService")
	AnswerService ansService;

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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Sono entrato nel doGet di PropII poichè richiamato da PropI");
		String path = "/html/fixedQuestions.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		templateEngine.process(path, ctx, response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// variables for the creation of answers and questionnaire
		Integer userID = null;
		Integer productID = null;

		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String exp_lvl = request.getParameter("expertise");
		User user = (User) request.getSession().getAttribute("user");
		Product product = (Product) request.getSession().getAttribute("product");
		
		Questionnaire questionnaire = qService.createQuestionnaire(age, exp_lvl, gender, product.getDate(), product, user);
		

		List<Marketingquestion> questions = (List<Marketingquestion>) request.getSession().getAttribute("questions");
		List <String >answers_prop = (List <String>) request.getSession().getAttribute("answers");		
		userID = user.getUserID();
		productID = product.getProductID();

		try {
			ansService.createAnswers(userID, productID, questions, answers_prop, questionnaire );
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
			return;
		}

		String path = "/html/greetings.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		templateEngine.process(path, ctx, response.getWriter());

	}

}
