package by.study.news.service.impl;

import java.util.Date;

import by.study.news.bean.User;
import by.study.news.bean.UserStatus;
import by.study.news.dao.UserDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;
import by.study.news.service.UserService;
import by.study.news.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	@Override
	public void registration(User user) throws ServiceException {

		try {

			if (userDAO.isLoginUsed(user.getLogin())) {
				throw new DAOException();
			}

		} catch (DAOException e) {

			throw new ServiceException("login is used, try another", e);
		}

		try {

			userDAO.registration(user);

		} catch (DAOException ex) {
			throw new ServiceException("registration service error", ex);
		}

	}

	@Override
	public User singIn(String login, String password) throws ServiceException {

		try {
			User user = userDAO.signIn(login, password);
			return user;

		} catch (DAOException e) {

			throw new ServiceException("singning in service error", e);
		}

	}

	@Override
	public void singOut(String login) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
