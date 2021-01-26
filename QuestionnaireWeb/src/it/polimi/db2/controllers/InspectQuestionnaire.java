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

import model.Product;
import model.Questionnaire;
import model.User;
import services.ProductService;
import services.QuestionnaireService;


//TODO basically everything
@WebServlet("/AdministrativeTools/InspectQuestionnaire")
public class InspectQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "services/ProductService")
	ProductService productService;   
    
	@EJB(name = "services/QuestionnaireService")
	QuestionnaireService questionnaireService;
    public InspectQuestionnaire() {
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
		
		//get all the user associated with a questionnaire
		String productID = request.getParameter("productID");
		Product product = productService.findProduct(Integer.parseInt(productID));
		List<Questionnaire> questList = questionnaireService.findByProduct(product);
		List<User> userList = new ArrayList<User>();
		questList.forEach(quest -> userList.add(quest.getUser()));
		
		//set the variable in the context
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("quests", questList);
		
		String path;
		path = "/html/inspectQuestionnaire.html";
		
		templateEngine.process(path, ctx, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
