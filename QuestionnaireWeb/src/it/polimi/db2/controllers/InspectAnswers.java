package it.polimi.db2.controllers;

import java.io.IOException;
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

import model.Answer;
import model.Questionnaire;
import services.AnswerService;
import services.ProductService;
import services.QuestionnaireService;


@WebServlet("/AdministrativeTools/InspectQuestionnaire/InspectAnswers")
public class InspectAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ProductService")
	ProductService productService;   
    
	@EJB(name = "services/QuestionnaireService")
	QuestionnaireService questionnaireService;
	
	@EJB(name = "services/AnswerService")
	AnswerService answerService;
    
    public InspectAnswers() {
        super();
       
    }

    
    public void init() {
    	ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("productID"));
		int userID = Integer.parseInt(request.getParameter("userID"));
		
		
		Questionnaire questionnaire = questionnaireService.findByPK(userID, productID);
		
		
		
		
		
		
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("quest", questionnaire);
		
		String path;
		path = "/html/inspectAnswers.html";
		
		templateEngine.process(path, ctx, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
