package by.study.news.controller.impl.article;

import java.io.IOException;

import java.util.Date;

import jakarta.servlet.http.HttpSession;

import by.study.news.bean.User;
import by.study.news.bean.Article;
import by.study.news.bean.ArticleStatus;
import by.study.news.bean.UserStatus;
import by.study.news.bean.UserRole;
import by.study.news.controller.Command;
import by.study.news.dao.UserDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;
import by.study.news.service.NewsService;
import by.study.news.service.UserService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteArticle implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameterValues("id") == null) {

			request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);

		} else {
			String selectedId = request.getParameter("id");

			try {
				newsService.deleteArticle(Integer.parseInt(selectedId));
			} catch (ServiceException e) {
				e.printStackTrace();

			}
			request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);

		}
	}
}