package by.study.news.dao.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import by.study.news.bean.User;
import by.study.news.bean.UserStatus;
import by.study.news.bean.UserRole;
import by.study.news.dao.UserDAO;
import by.study.news.dao.connection.ConnectionPool;
import by.study.news.dao.connection.ConnectionPoolException;
import by.study.news.dao.exception.DAOException;
import java.sql.ResultSet;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

public class SQLUserDAO implements UserDAO {

	private static final String COLUMN_ID = "id";
	private static final String COLUMN_LOGIN = "login";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_ROLE = "role";
	private static final String COLUMN_STATUS = "status";

	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_LAST_NAME = "last_name";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_REGISTER_DATE = "register_date";

	private static final String ACTIVE = "ACTIVE";
	private static final String BLOCKED = "BLOCKED";

	private static final String INSERT_USERS_QUERY = "insert into users(login, password, status) values(?, ?, ?)";
	private static final String INSERT_USER_DETAILS_QUERY = "insert into user_details(name, last_name, email, register_date, users_id) values(?, ?, ?, ?, ?)";
	private static final String SELECT_USER_AND_DETAILS_BY_ID_QUERY = "SELECT USERS.ID, USERS.LOGIN, USERS.PASSWORD, USERS.ROLE, USERS.STATUS, USER_DETAILS.NAME, USER_DETAILS.LAST_NAME, USER_DETAILS.EMAIL, USER_DETAILS.REGISTER_DATE FROM USERS INNER JOIN USER_DETAILS ON USERS.ID = USER_DETAILS.USERS_ID WHERE USERS.ID = ?";
	private static final String SELECT_USER_AND_DETAILS_BY_LOGIN_QUERY = "SELECT USERS.ID, USERS.LOGIN, USERS.PASSWORD, USERS.ROLE, USERS.STATUS, USER_DETAILS.NAME, USER_DETAILS.LAST_NAME, USER_DETAILS.EMAIL, USER_DETAILS.REGISTER_DATE FROM USERS INNER JOIN USER_DETAILS ON USERS.ID = USER_DETAILS.USERS_ID WHERE USERS.LOGIN = ?";
	private static final String SELECT_USER_BY_ID_QUERY = "select * from users where id = ?";
	private static final String SELECT_USER_BY_LOGIN_QUERY = "select * from users where login=?";
	private static final String BLOCK_USER_QUERY = "UPDATE users set status = ? where id = ? ";
	private static final String ACTIVATE_USER_QUERY = "UPDATE users set status = ? where id = ?";
	private static final String UDDATE_USER_AND_DETAILS = "UPDATE USERS JOIN USER_DETAILS ON USERS.ID = USER_DETAILS.USERS_ID	SET USERS.PASSWORD = ?, USER_DETAILS.NAME = ?, USER_DETAILS.LAST_NAME = ?, USER_DETAILS.EMAIL = ? where USERS.ID = ?";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	public void registration(User user) throws DAOException {

		Connection connection = null;

		try {
			connection = connectionPool.takeConnection();

			connection.setAutoCommit(false);
			
			fillUserDetails(user, fillUsers(user, connection), connection);
			connection.commit();

		} catch (SQLException | ConnectionPoolException e) {
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				throw new DAOException("rollback error", e);
			}
			throw new DAOException("registration error", e);

		} finally {
			try {
				if (connection != null) {
					connection.setAutoCommit(true);
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User signIn(String login, String password) throws DAOException {

		User user = getByLogin(login);

		if (user.getStatus() != UserStatus.ACTIVE) {
			throw new DAOException("signing in error. user status BLOCKED");

		}

		if (BCrypt.checkpw(password, user.getPassword()) != true) {
			throw new DAOException("signing in error. wrong password");

		}

		return user;
	}

	@Override
	public User getById(int id) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(SELECT_USER_AND_DETAILS_BY_ID_QUERY);) {
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(COLUMN_ID));
				user.setLogin(resultSet.getString(COLUMN_LOGIN));
				user.setPassword(resultSet.getString(COLUMN_PASSWORD));
				user.setRole(UserRole.valueOf(resultSet.getString(COLUMN_ROLE)));
				user.setStatus(UserStatus.valueOf(resultSet.getString(COLUMN_STATUS)));
				user.setName(resultSet.getString(COLUMN_NAME));
				user.setLastName(resultSet.getString(COLUMN_LAST_NAME));
				user.setEmail(resultSet.getString(COLUMN_EMAIL));
				user.setDate(resultSet.getDate(COLUMN_REGISTER_DATE));

				return user;
			}

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("getting user by id error", e);
		}
		throw new DAOException("user not found");

	}

	@Override
	public void block(int id) throws DAOException {

		isIdReal(id);
		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(BLOCK_USER_QUERY);) {

			stmt.setString(1, BLOCKED);
			stmt.setInt(2, id);
			stmt.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("error blocking user", e);
		}

	}

	@Override
	public void activate(int id) throws DAOException {
		isIdReal(id);
		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(ACTIVATE_USER_QUERY);) {

			stmt.setString(1, ACTIVE);
			stmt.setInt(2, id);
			stmt.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("error activating user", e);
		}
	}

	@Override
	public void update(User user) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(UDDATE_USER_AND_DETAILS);

		) {

			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getEmail());
			stmt.setInt(5, user.getId());
			stmt.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("error updating user", e);

		}
	}

	@Override
	public boolean isLoginUsed(String login) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);) {

			stmt.setString(1, login);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return true;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("checking isLoginUsed error", e);
		}
		return false;
	}

	private User getByLogin(String login) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(SELECT_USER_AND_DETAILS_BY_LOGIN_QUERY);) {
			stmt.setString(1, login);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(COLUMN_ID));
				user.setLogin(resultSet.getString(COLUMN_LOGIN));
				user.setPassword(resultSet.getString(COLUMN_PASSWORD));
				user.setRole(UserRole.valueOf(resultSet.getString(COLUMN_ROLE)));
				user.setStatus(UserStatus.valueOf(resultSet.getString(COLUMN_STATUS)));
				user.setName(resultSet.getString(COLUMN_NAME));
				user.setLastName(resultSet.getString(COLUMN_LAST_NAME));
				user.setEmail(resultSet.getString(COLUMN_EMAIL));
				user.setDate(resultSet.getDate(COLUMN_REGISTER_DATE));

				return user;
			}

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("getting user by login error", e);
		}
		throw new DAOException("user not found");

	}

	private void isIdReal(int id) throws DAOException {

		try (Connection connection = connectionPool.takeConnection();

				PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_ID_QUERY);

		) {

			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();

			if (!resultSet.next()) {

				throw new DAOException("user with id = " + id + " not found");

			}

		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
			throw new DAOException("checking users id error", e);
		}

	}

	private int fillUsers(User user, Connection connection) throws SQLException {

		PreparedStatement stmtUsers = connection.prepareStatement(INSERT_USERS_QUERY, Statement.RETURN_GENERATED_KEYS);

		stmtUsers.setString(1, user.getLogin());
		stmtUsers.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		stmtUsers.setString(3, user.getStatus().name());
		stmtUsers.executeUpdate();

		ResultSet keys = stmtUsers.getGeneratedKeys();
		int lastKey = 1;
		while (keys.next()) {
			lastKey = keys.getInt(1);
		}
		return lastKey;

	}

	private void fillUserDetails(User user, int id, Connection connection) throws SQLException {

		PreparedStatement stmtUserDetails = connection.prepareStatement(INSERT_USER_DETAILS_QUERY);

		stmtUserDetails.setString(1, user.getName());
		stmtUserDetails.setString(2, user.getLastName());
		stmtUserDetails.setString(3, user.getEmail());
		stmtUserDetails.setObject(4, user.getDate());
		stmtUserDetails.setInt(5, id);
		stmtUserDetails.executeUpdate();
	}

}
