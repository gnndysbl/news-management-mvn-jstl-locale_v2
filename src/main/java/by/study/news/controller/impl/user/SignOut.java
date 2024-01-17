package by.study.news.controller.impl.user;

import java.io.IOException;


import java.util.Date;

import jakarta.servlet.http.HttpSession;

import by.study.news.bean.User;
import by.study.news.bean.UserStatus;
import by.study.news.bean.UserRole;
import by.study.news.controller.Command;
import by.study.news.dao.UserDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;
import by.study.news.service.UserService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignOut implements Command {
	 
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
			
			HttpSession oldSession = request.getSession(true);
			String currentLocale = (String) oldSession.getAttribute("local");	
			oldSession.invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("local", currentLocale);
           
			response.sendRedirect("controller?command=go_to_base_page");

		
	}
}
