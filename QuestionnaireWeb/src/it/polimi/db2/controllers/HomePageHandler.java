package it.polimi.db2.controllers;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import model.Product;
import model.Review;
import model.User;
import services.ProductService;
import services.ReviewService;
//import model.User;

@WebServlet("/Home")
public class HomePageHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
    @EJB(name="services/ProductService")   
	ProductService productService;
    @EJB(name="services/ReviewService")   
	ReviewService reviewService;

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
		Product products = null;

		try {
			products = productService.getProductOfTheDay();
			if(products == null) {
				String path = "html/noProduct.html";
				ServletContext servletContext = getServletContext();
				final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
				templateEngine.process(path, ctx, response.getWriter());
				return;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Review> reviews=reviewService.findReviewsOfAProduct(products);
		String path = "html/homepage.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("product", products);
		ctx.setVariable("reviews", reviews);

		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}
}
