package by.study.news.service;

import java.util.List;

import by.study.news.bean.Article;
import by.study.news.dao.exception.DAOException;
import by.study.news.service.exception.ServiceException;

public interface NewsService {
	
	void addArticle(Article article) throws ServiceException;
	void deleteArticle(int id) throws ServiceException;
	void editArticle(Article article) throws ServiceException;
	Article getArticle(int id) throws ServiceException;
	List<Article> getAllArticles() throws ServiceException;
	List<Article> getLast(int quantity) throws ServiceException;
	List<Article> getNewsPaged(int quantity, int page) throws ServiceException;


}
