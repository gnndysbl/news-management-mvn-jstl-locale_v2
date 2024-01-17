package by.study.news.controller.impl.user;

import java.io.IOException;

import java.util.List;

import by.study.news.bean.Article;
import by.study.news.bean.User;
import by.study.news.controller.Command;
import by.study.news.service.UserService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.NewsService;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {

	private UserService userService = ServiceFactory.getInstance().getUserService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			User user = userService.singIn(request.getParameter("login"), request.getParameter("password"));

			HttpSession session = request.getSession(true);
			session.setAttribute("user", "active");
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userRole", user.getRole());

			
			response.sendRedirect("controller?command=go_to_base_page");

			System.out.println("signed in");
			System.out.println(session.getAttribute("user"));
			System.out.println(session.getAttribute("userName"));
			System.out.println(session.getAttribute("userRole"));

			
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect("controller?command=go_to_base_page&signingInError=signing in error");
		}

	}
}
