package by.study.news.service;

import by.study.news.bean.User;
import by.study.news.service.exception.ServiceException;

public interface UserService {
	
	User singIn(String login, String password) throws ServiceException;

	void singOut(String login) throws ServiceException;

	void registration(User user) throws ServiceException;

}