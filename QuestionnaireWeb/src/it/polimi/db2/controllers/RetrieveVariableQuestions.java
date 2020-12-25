package it.polimi.db2.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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

import exceptions.MarketingQuestionException;
import model.Marketingquestion;
import model.Product;
import model.User;
import services.MarketingQuestionService;
import services.ProductService;


@WebServlet("/RetrieveVariableQuestions")
public class RetrieveVariableQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "services/MarketingQuestionService")
	MarketingQuestionService qService;
	@EJB(name = "services/ProductService")
	ProductService pService;
	
	 public void init() throws ServletException {
			ServletContext servletContext = getServletContext();
			ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
			templateResolver.setTemplateMode(TemplateMode.HTML);
			this.templateEngine = new TemplateEngine();
			this.templateEngine.setTemplateResolver(templateResolver);
			templateResolver.setSuffix(".html");
		}
	
	
    public RetrieveVariableQuestions() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginpath = getServletContext().getContextPath() + "/index.html";
		
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			response.sendRedirect(loginpath);
			return;
		}
		
		//active session with logged user
		//get current date to be passed to findProduct product service
		//TODO: remember to handle different time zone
		List <Marketingquestion> questions = null;
		Product products = null;
		Date startDate = null;
		
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDateTime now = LocalDateTime.now();
	        String current_date = (String) dtf.format(now);
			startDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(current_date).getTime());
			System.out.println(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}		
		// retrieve the product associated with the current date and the marketing questions associated with the given product
		try {
			products = pService.findProduct(startDate);
			System.out.println("Ho trovato il product con la data odierna"+products);
			questions = qService.retrieveQuestions(products);
			for (Marketingquestion question : questions) {
				System.out.println("Ho trovato le domande associate a tale product e sono"+question.getDescription());
			}
			//System.out.println("Ho trovato le domande associate a tale product e sono"+questions);
		} catch (MarketingQuestionException e) {
			//cannot retrieve the data 
			return;
		}
		String path = "/html/variableQuestions.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("product", products);
		ctx.setVariable("questions", questions);
		templateEngine.process(path, ctx, response.getWriter());

	}


}
