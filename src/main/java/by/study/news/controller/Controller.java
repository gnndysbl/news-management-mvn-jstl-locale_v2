package by.study.news.controller;

import java.io.IOException;

import by.study.news.dao.connection.ConnectionPool;
import by.study.news.dao.connection.ConnectionPoolException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/controller")

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = CommandProvider.getInstance();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String name = request.getParameter("command");

		Command command = provider.getCommand(name);

		command.execute(request, response);

	}

	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ConnectionPool.getInstance().initPoolData();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException(e);
		}
	}

}
