package it.polimi.db2.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import model.User;

//This filter check for admin permissions
@WebFilter("/AdministrativeTools/*")
public class LoginFilter implements Filter {
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private ServletContext servletContext;
	private TemplateEngine templateEngine;
    public LoginFilter() {
        
    }
    
   

	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		httpRequest = (HttpServletRequest) request;
		httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		
        if(session != null && session.getAttribute("user") != null) {
        	User user = (User) session.getAttribute("user");
        	if(!user.getAdmin()) {
            	this.servletContext.log("You're not authorized");
            	httpResponse.sendRedirect("Home");
            	return;
            }
        } else {
        	httpResponse.sendRedirect("/QuestionnaireWeb");
        	return;
        }
        
        
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.servletContext = fConfig.getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}

}
