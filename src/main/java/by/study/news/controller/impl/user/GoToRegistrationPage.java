package by.study.news.controller.impl.user;

import java.io.IOException;

import by.study.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistrationPage implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute("targetLink", "controller?command=go_to_registration_page");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
		requestDispatcher.forward(request, response);
		
		
	}
}
