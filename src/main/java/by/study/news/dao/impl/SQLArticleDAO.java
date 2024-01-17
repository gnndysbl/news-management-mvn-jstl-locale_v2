package by.study.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.study.news.dao.connection.ConnectionPool;
import by.study.news.dao.connection.ConnectionPoolException;
import by.study.news.bean.Article;
import by.study.news.bean.ArticleStatus;
import by.study.news.dao.NewsDAO;
import by.study.news.dao.exception.DAOException;

public class SQLArticleDAO implements NewsDAO {

	private static final String COLUMN_ID = "id";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_CONTENT = "content";
	private static final String COLUMN_BRIEF = "brief";
	private static final String COLUMN_STATUS = "status";
	private static final String COLUMN_ARTICLE_DATE = "article_date";
	private static final String COLUMN_USERS_ID = "users_id";

	private static final String ACTIVE = "ACTIVE";
	private static final String BLOCKED = "BLOCKED";

	private static final String INSERT_NEWS_QUERY = "insert into news(title, brief, content, article_date, status, users_id) values(?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ARTICLE_BY_ID_QUERY = "select * from news where id = ?";
	private static final String SELECT_ARTICLE_FROM_TO_QUERY = "select * from news where id between ? and ?";
	private static final String SELECT_ALL_ARTICLES_QUERY = "select * from news where status = ?";
	private static final String BLOCK_ARTICLE_QUERY = "UPDATE news set status = ? where id = ? ";
	private static final String ACTIVATE_ARTICLE_QUERY = "UPDATE news set status = ? where id = ?";

	private static final String UPDATE_ARTICLE_QUERY = "UPDATE news set title = ?, brief = ?, content = ?  where id = ? ";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void add(Article article) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(INSERT_NEWS_QUERY);) {

			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getBrief());
			stmt.setString(3, article.getContent());
			stmt.setObject(4, article.getDate());
			stmt.setString(5, article.getStatus().name());
			stmt.setInt(6, article.getUserId());
			stmt.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("article placement error", e);
		}

	}

	@Override
	public void block(int id) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(BLOCK_ARTICLE_QUERY);) {

			stmt.setString(1, BLOCKED);
			stmt.setInt(2, id);
			stmt.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("article blocking error", e);

		}
	}

	@Override
	public void activate(int id) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(ACTIVATE_ARTICLE_QUERY);) {

			stmt.setString(1, ACTIVE);
			stmt.setInt(2, id);
			stmt.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("article activating error", e);

		}
	}

	@Override
	public void edit(Article article) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(UPDATE_ARTICLE_QUERY);) {

			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getBrief());
			stmt.setString(3, article.getContent());
			stmt.setInt(4, article.getId());

			int result = stmt.executeUpdate();

			if (result == 0) {
				throw new DAOException("error updating news in the system");
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("editting error", e);
		}

	}

	@Override
	public Article getById(int id) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(SELECT_ARTICLE_BY_ID_QUERY);) {

			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {

				return new Article(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_TITLE),
						resultSet.getString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
						resultSet.getDate(COLUMN_ARTICLE_DATE),
						ArticleStatus.valueOf(resultSet.getString(COLUMN_STATUS)), resultSet.getInt(COLUMN_USERS_ID));

			} else {
				throw new DAOException("article not found");
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("getting article by id error", e);
		}

	}

	public List<Article> getById(int from, int to) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(SELECT_ARTICLE_FROM_TO_QUERY);) {

			stmt.setInt(1, from);
			stmt.setInt(2, to);

			ResultSet resultSet = stmt.executeQuery();

			List<Article> list = new ArrayList<>();
			while (resultSet.next()) {

				list.add(new Article(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_TITLE),
						resultSet.getString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
						resultSet.getDate(COLUMN_ARTICLE_DATE),
						ArticleStatus.valueOf(resultSet.getString(COLUMN_STATUS)), resultSet.getInt(COLUMN_USERS_ID)));
			}
			return list;

		} catch (SQLException | ConnectionPoolException e1) {

			throw new DAOException("getting articles from to error", e1);
		}
	}

	@Override
	public List<Article> getAll() throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_ARTICLES_QUERY);) {
			stmt.setString(1, ACTIVE);

			ResultSet resultSet = stmt.executeQuery();
			List<Article> list = new ArrayList<>();

			while (resultSet.next()) {

				list.add(new Article(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_TITLE),
						resultSet.getString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
						resultSet.getDate(COLUMN_ARTICLE_DATE),
						ArticleStatus.valueOf(resultSet.getString(COLUMN_STATUS)), resultSet.getInt(COLUMN_USERS_ID)));

			}

			int size = list.size();

			if (size == 0) {
				return null;

			}
			return list;
		} catch (SQLException | ConnectionPoolException e) {

			e.printStackTrace();
			throw new DAOException("getting all articles error", e);
		}

	}

	@Override
	public List<Article> getLast(int quantity) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection
						.prepareStatement("SELECT * FROM news where status = ? ORDER BY id DESC LIMIT ?")) {
			stmt.setString(1, ACTIVE);
			stmt.setInt(2, quantity);

			ResultSet resultSet = stmt.executeQuery();
			List<Article> list = new ArrayList<>();

			while (resultSet.next()) {

				list.add(new Article(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_TITLE),
						resultSet.getString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
						resultSet.getDate(COLUMN_ARTICLE_DATE),
						ArticleStatus.valueOf(resultSet.getString(COLUMN_STATUS)), resultSet.getInt(COLUMN_USERS_ID)));

			}

			int size = list.size();

			if (size == 0) {
				return null;

			}
			return list;
		} catch (SQLException | ConnectionPoolException e) {

			e.printStackTrace();
			throw new DAOException("getting all articles error", e);
		}

	}

	public List<Article> getNewsPaged(int quantity, int page) throws DAOException {

		int gap = page * quantity;
		
		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection
						.prepareStatement("SELECT * FROM news where status = ? ORDER BY id DESC LIMIT ? , ?")) {
			stmt.setString(1, ACTIVE);
			stmt.setInt(2, gap);
			stmt.setInt(3, quantity);

			ResultSet resultSet = stmt.executeQuery();
			List<Article> list = new ArrayList<>();

			while (resultSet.next()) {

				list.add(new Article(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_TITLE),
						resultSet.getString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
						resultSet.getDate(COLUMN_ARTICLE_DATE),
						ArticleStatus.valueOf(resultSet.getString(COLUMN_STATUS)), resultSet.getInt(COLUMN_USERS_ID)));

			}

			int size = list.size();

			if (size == 0) {
				return null;

			}
			return list;
		} catch (SQLException | ConnectionPoolException e) {

			e.printStackTrace();
			throw new DAOException("getting news paged error", e);
		}

	}
}
