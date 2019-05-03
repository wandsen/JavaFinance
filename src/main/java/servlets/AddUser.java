package servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.User.User;
import com.fdmgroup.User.UserDao;

public class AddUser extends HttpServlet {

	Logger logger = LogManager.getLogger();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserDao ud = new UserDao((EntityManagerFactory) this.getServletContext().getAttribute("emf"));

		// Check if user name exist
		User returnedUser = ud.getUserByName(req.getParameter("name"));

		logger.trace("Checking if username exist from database returnedUser: " + returnedUser);

		if (returnedUser == null) {
			User user = new User();
			user.setName(req.getParameter("name"));
			user.setPassword(req.getParameter("password"));

			logger.trace("username input by user: " + req.getParameter("name") + "password input by user: "
					+ req.getParameter("password"));
			logger.trace("Calling UserDao.adduser with the following parameter " + user);
			ud.addUser(user);
			
			req.setAttribute("user", user);
			req.getRequestDispatcher("./index.jsp").forward(req, resp);
		} else {
			logger.trace("User already exist in database");
			String errorMsg = "Your username is not available";
			req.setAttribute("errorMsg", errorMsg);
			req.getRequestDispatcher("./WEB-INF/registration.jsp").forward(req, resp);
		}

	}

}
