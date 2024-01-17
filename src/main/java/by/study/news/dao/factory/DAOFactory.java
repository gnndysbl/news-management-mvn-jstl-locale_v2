package by.study.news.dao.factory;

import by.study.news.bean.User;
import by.study.news.dao.NewsDAO;
import by.study.news.dao.UserDAO;
import by.study.news.dao.impl.SQLArticleDAO;
import by.study.news.dao.impl.SQLUserDAO;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final NewsDAO sqlArticleImpl = new SQLArticleDAO();
	private final UserDAO sqlUserImpl = new SQLUserDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public NewsDAO getArticleDAO() {
		return sqlArticleImpl;
	}

	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}

}