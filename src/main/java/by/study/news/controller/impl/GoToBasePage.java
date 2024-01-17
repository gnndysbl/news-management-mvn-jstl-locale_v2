package by.study.news.controller.impl;

import java.io.IOException;

import java.util.List;

import by.study.news.bean.Article;
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

public class GoToBasePage implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Article> lastNews;

		try {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("newsPerPage", 3);

			lastNews = newsService.getLast((Integer) session.getAttribute("newsPerPage"));
			
			request.setAttribute("news", lastNews);
			session.setAttribute("targetLink", "controller?command=go_to_base_page");
			session.setAttribute("viewArticle", null);
			session.setAttribute("addArticle", null);
			session.setAttribute("editArticle", null);
			session.setAttribute("page", 1);

			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
	
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
}
