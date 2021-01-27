package it.polimi.db2.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;

import model.User;
import services.AccessTimeService;
import services.UserService;

/**
 * Servlet implementation class CancelQuestionnaire
 */
@WebServlet("/CancelQuestionnaire")
public class CancelQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB(name="services/AccessTimeService")   
	AccessTimeService accessTimeService;
   

    public CancelQuestionnaire() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Date startDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String current_date = (String) dtf.format(now);
		try {
			startDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(current_date).getTime());
			accessTimeService.createAccessTime(startDate, user);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String path = "/QuestionnaireWeb/Home";
		System.out.println(path);
		response.sendRedirect(path);
	}

}
