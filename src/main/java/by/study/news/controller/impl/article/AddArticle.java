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

public class AddArticle implements Command {

	private NewsService newsService = ServiceFactory.getInstance().getNewsService();

	private static final String TITLE_PARAM = "title";
	private static final String BRIEF_PARAM = "brief";
	private static final String CONTENT_PARAM = "content";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("entered AddArticle class");
		
		HttpSession session = request.getSession(true);
		int id = (Integer) session.getAttribute("userId");

		Article article = new Article(request.getParameter(TITLE_PARAM), request.getParameter(BRIEF_PARAM),
				request.getParameter(CONTENT_PARAM), ArticleStatus.ACTIVE, id);

		try {
			newsService.addArticle(article);

			response.sendRedirect("controller?command=go_to_base_page");

		} catch (ServiceException e) {
			e.getMessage();
			e.printStackTrace();

//			if (e.getMessage().equalsIgnoreCase(LOGIN_IS_USED_MESSAGE)) {
//
//				response.sendRedirect(
//						"controller?command=go_to_registration_page&registrationError=login is used, try another");
//
//			} else {
//
//				response.sendRedirect(
//						"controller?command=go_to_registration_page&registrationError=registration error");
//			}
		}
	}
}
