package it.polimi.db2.controllers;

import java.io.IOException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.User;
import services.ProductService;
import services.ReviewService;

/**
 * Servlet implementation class CreateReview
 */
@WebServlet("/CreateReview")
public class CreateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB(name="services/ProductService")   
	ProductService productService;
    @EJB(name="services/ReviewService")   
	ReviewService reviewService;

	
    public CreateReview() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewText=request.getParameter("reviewText");
		System.out.println(reviewText);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println("user logged: " +user.getUsername());
		try {
			Product productOfTheDay=productService.getProductOfTheDay();
			if(productOfTheDay == null) {
				String ctxpath = getServletContext().getContextPath();
				String path = ctxpath + "/Home";
				response.sendRedirect(path);
				return;
			}
			System.out.println("product of the day: "+productOfTheDay.getName());
			reviewService.createReview(user, reviewText, productOfTheDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String ctxpath = getServletContext().getContextPath();
		String path = ctxpath + "/Home";
		response.sendRedirect(path);

	}

}
