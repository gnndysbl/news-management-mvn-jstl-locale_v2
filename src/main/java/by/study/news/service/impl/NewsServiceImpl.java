package by.study.news.service.impl;

import java.util.Date;
import java.util.List;
import by.study.news.bean.Article;
import by.study.news.dao.NewsDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;
import by.study.news.service.*;
import by.study.news.service.exception.ServiceException;
import jakarta.servlet.http.HttpSession;

public class NewsServiceImpl implements NewsService {

	private final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();

	@Override
	public void addArticle(Article article) throws ServiceException {

		article.setDate(new Date());

		try {
			newsDAO.add(article);
		} catch (DAOException e) {
			throw new ServiceException("adding article service error");
		}

	}

	@Override
	public void deleteArticle(int id) throws ServiceException {

		try {
			newsDAO.block(id);
		} catch (DAOException e) {
			throw new ServiceException("deleting article service error");
		}

	}

	@Override
	public void editArticle(Article article) throws ServiceException {

		try {
			newsDAO.edit(article);
		} catch (DAOException e) {
			throw new ServiceException("edition article error");
		}

	}

	@Override
	public Article getArticle(int id) throws ServiceException {

		try {
			Article article = newsDAO.getById(id);
			return article;
		} catch (DAOException e) {
			throw new ServiceException("getting article by id error");
		}

	}

	@Override
	public List<Article> getAllArticles() throws ServiceException {

		try {
			List<Article> list = newsDAO.getAll();
			return list;

		} catch (DAOException e) {
			throw new ServiceException("getting all articles error");
		}

	}

	@Override
	public List<Article> getLast(int quantity) throws ServiceException {

		try {

			List<Article> list = newsDAO.getLast(quantity);
			return list;

		} catch (DAOException e) {
			throw new ServiceException("getting all articles error");
		}

	}

	@Override
	public List<Article> getNewsPaged(int quantity, int page) throws ServiceException {
		
		try {

			List<Article> list = newsDAO.getNewsPaged(quantity, (page - 1));
			return list;

		} catch (DAOException e) {
			throw new ServiceException("getting news paged error");
		}

	}
}