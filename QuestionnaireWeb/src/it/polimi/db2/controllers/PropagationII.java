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
	
	@EJB(name = "services/UserService")
	UserService usrService;

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
		//System.out.println("Sono entrato nel doGet di PropII poichè richiamato da PropI");
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
		List<Marketingquestion> questions = (List<Marketingquestion>) request.getSession().getAttribute("questions");
		List <String >answers_prop = (List <String>) request.getSession().getAttribute("answers");		
		userID = user.getUserID();
		productID = product.getProductID();
		
		
		//check sullo stato bannato dell'utente in sessione, se è bannato response.sendError. se non lo è verifichiamo se le sue risposte
		//alle domande contengono parole offensive, se le contengono l'utente viene bannato e saltiamo a response.sendError
		//se invece non contengono parolacce creiamo dapprima il questionario e dopodichè inseriamo le risposte.
		
		if(user.getBlocked()==false) {
			System.out.println("Ho verificato che l'utente non risulta essere blocked");
			try {
				boolean ret = ansService.checkAnswers(answers_prop);
				if (ret == false) {
					System.out.println("Sono in PropII ed ho verificato che le offensiveword sono assenti sto dunque procedendo alla creazione del questionario: "+ret);
					Questionnaire questionnaire = qService.createQuestionnaire(age, exp_lvl, gender, product.getDate(), product, user);
					System.out.println("Sono in PropII ho creato il questionario sto procedendo all'inserimento delle risposte");
					ansService.createAnswers(userID, productID, questions, answers_prop, questionnaire );
					String path = "/html/greetings.html";
					ServletContext servletContext = getServletContext();
					final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
					templateEngine.process(path, ctx, response.getWriter());
				}
				else {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Offensiveword in the answers, now you're banned");
					//TODO: funzione di ban dell'utente
					System.out.println("Fai finta che ho bloccato l'utente");
					return;
				}
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
				return;
			}		
		}
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Banned user cannot insert questionnaire");
			return;
		}

	}
	
	
	/*
	 * 
	 * Questionnaire questionnaire = qService.createQuestionnaire(age, exp_lvl, gender, product.getDate(), product, user);
	 * List<Marketingquestion> questions = (List<Marketingquestion>) request.getSession().getAttribute("questions");
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
	 */

}
