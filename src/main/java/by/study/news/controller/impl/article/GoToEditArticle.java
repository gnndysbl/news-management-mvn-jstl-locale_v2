package by.study.news.controller.impl.article;

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

public class GoToEditArticle implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		int id = Integer.parseInt(request.getParameter("id"));
				
		session.setAttribute("viewArticle", null);
		session.setAttribute("addArticle", null);
		session.setAttribute("editArticle", "active");
		session.setAttribute("targetLink", "controller?command=go_to_edit_article&id=" + request.getParameter("id"));


		try {
			Article article = newsService.getArticle(id);
			session.setAttribute("news", article);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp");
		requestDispatcher.forward(request, response);
	}

}
