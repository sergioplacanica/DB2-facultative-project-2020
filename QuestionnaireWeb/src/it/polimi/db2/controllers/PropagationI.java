package it.polimi.db2.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import model.Answer;
import model.AnswerPK;
import model.Marketingquestion;
import model.Product;
import model.User;




@WebServlet("/PropagationI")
public class PropagationI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	public PropagationI() {
		super();
	}

	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		
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
		
		List <Marketingquestion> questions = (List <Marketingquestion>) request.getSession().getAttribute("questions");
		List<String> answers_text= new ArrayList<String>();
		for(int x=0 ; x< questions.size(); x++) {
			
			answers_text.add(request.getParameter(""+x));
		}
		
		request.getSession().setAttribute("answers", answers_text);
		
		String ctxpath = getServletContext().getContextPath();
		String path = ctxpath + "/PropagationII";
		response.sendRedirect(path);
		
		

	}

}
