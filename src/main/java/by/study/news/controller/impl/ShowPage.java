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

public class ShowPage implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Article> lastNews;

		HttpSession session = request.getSession(true);

		try {
			lastNews = newsService.getNewsPaged((Integer) session.getAttribute("newsPerPage"),
					(Integer) session.getAttribute("page"));

			request.setAttribute("news", lastNews);

			session.setAttribute("targetLink", "controller?command=show_page");
			session.setAttribute("viewArticle", null);
			session.setAttribute("addArticle", null);
			session.setAttribute("editArticle", null);

			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
}
