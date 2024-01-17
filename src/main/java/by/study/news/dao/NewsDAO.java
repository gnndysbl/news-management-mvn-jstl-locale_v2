package by.study.news.dao;

import java.util.List;

import by.study.news.bean.Article;
import by.study.news.dao.exception.DAOException;

public interface NewsDAO {
	
	void add(Article article) throws DAOException;
	void block(int id) throws DAOException;
	void activate(int id) throws DAOException;
	void edit(Article article) throws DAOException;
	Article getById(int id) throws DAOException;
	List<Article> getById(int from, int to) throws DAOException;
	List<Article> getAll() throws DAOException;
	List<Article> getLast(int quantity) throws DAOException;
	List<Article> getNewsPaged(int quantity, int page) throws DAOException;
	
	
	}


