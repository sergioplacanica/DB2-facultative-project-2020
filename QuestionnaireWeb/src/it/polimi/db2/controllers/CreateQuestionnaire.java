package it.polimi.db2.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ProductService;

/**
 * Servlet implementation class CreateQuestionnaire
 */
@WebServlet("/html/CreateQuestionnaire")
public class CreateQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB(name="services/ProductService")   
    ProductService productService;
    
    public CreateQuestionnaire() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName= request.getParameter("productName");
		System.out.println(productName);
		String imagePath=request.getParameter("imagePath");
		System.out.println(imagePath);
		String[] liValues = request.getParameterValues("question");
		for(String listElement : liValues) {
			System.out.println(listElement);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = (Date) sdf.parse(request.getParameter("date"));
			System.out.println(startDate);
			productService.createProduct(productName, imagePath,startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String ctxpath = getServletContext().getContextPath();
		String path = ctxpath + "/html/createQuestionnaire.html";
		System.out.println(path);
		response.sendRedirect(path);
	}

}
