package by.study.news.dao;

import java.util.List;

import by.study.news.bean.User;
import by.study.news.dao.exception.DAOException;

public interface UserDAO {
	
	User signIn(String login, String password) throws DAOException;
	void registration(User user) throws DAOException;
	User getById (int id) throws DAOException;
	void block (int id) throws DAOException;
	void activate (int id) throws DAOException;
	void update(User user) throws DAOException;
	boolean isLoginUsed (String login) throws DAOException;
	
}
