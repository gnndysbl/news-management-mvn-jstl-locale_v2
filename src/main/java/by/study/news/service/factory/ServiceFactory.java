package by.study.news.service.factory;

import by.study.news.service.NewsService;
import by.study.news.service.UserService;

import by.study.news.service.impl.NewsServiceImpl;
import by.study.news.service.impl.UserServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final NewsService newsService = new NewsServiceImpl();
	private final UserService userService = new UserServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public UserService getUserService() {
		return userService;

	}
}