package by.study.news.controller.impl.user;

import java.io.IOException;


import java.util.Date;

import jakarta.servlet.http.HttpSession;

import by.study.news.bean.User;
import by.study.news.bean.UserStatus;
import by.study.news.bean.UserRole;
import by.study.news.controller.Command;
import by.study.news.dao.UserDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;
import by.study.news.service.UserService;
import by.study.news.service.exception.ServiceException;
import by.study.news.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddUser implements Command {

	private UserService userService = ServiceFactory.getInstance().getUserService();

	 private static final String NAME_PARAM = "name";
	 private static final String LAST_NAME_PARAM = "lastName";
	 private static final String LOGIN_PARAM = "login";
	 private static final String EMAIL_PARAM = "email";
	 private static final String PASSWORD_PARAM = "password";
	
	 private static final String LOGIN_IS_USED_MESSAGE = "login is used, try another";

	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		User user = new User(	request.getParameter(NAME_PARAM),
								request.getParameter(LAST_NAME_PARAM),
								request.getParameter(LOGIN_PARAM),
								request.getParameter(EMAIL_PARAM),
								request.getParameter(PASSWORD_PARAM)
				);
		
		
		user.setDate(new Date());
		user.setStatus(UserStatus.ACTIVE);
		user.setRole(UserRole.USER);
		
		try {
			userService.registration(user);
			HttpSession session = request.getSession(true);
            session.setAttribute("user", "active");
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole());
                   
			response.sendRedirect("controller?command=go_to_base_page");

		} catch (ServiceException e) {
			e.getMessage();
			e.printStackTrace();

			if (e.getMessage().equalsIgnoreCase(LOGIN_IS_USED_MESSAGE)) {
					
				response.sendRedirect("controller?command=go_to_registration_page&registrationError=login is used, try another");
				
			} else {
			
			response.sendRedirect("controller?command=go_to_registration_page&registrationError=registration error");
			}
		}
	}
}
