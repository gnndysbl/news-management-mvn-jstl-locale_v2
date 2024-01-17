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

public class ChangeLocaleToEn implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute("local", "en");
		response.sendRedirect((String) session.getAttribute("targetLink"));

	}
}
