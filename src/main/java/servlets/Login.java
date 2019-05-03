package servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.User.User;
import com.fdmgroup.User.UserDao;

public class Login extends HttpServlet {
	Logger logger = LogManager.getLogger();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManagerFactory emf = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		UserDao ud = new UserDao(emf);

		// Check credentials
		User returnedUser = ud.getUserByName(req.getParameter("name"));
		boolean isNameCorrect = false;
		boolean isPasswordCorrect = false;
		
		if (returnedUser != null && returnedUser.getName().equals(req.getParameter("name"))) {
			logger.trace("User found: " + req.getParameter("name"));
			isNameCorrect = true;
		} else {
			logger.trace("No such user found");
			req.setAttribute("errorUsername", "true");
		}
		
		if (returnedUser != null && returnedUser.getPassword().equals(req.getParameter("password"))) {
			logger.trace("Password is correct: " + req.getParameter("password"));
			isPasswordCorrect = true;
		} else {
			logger.trace("Wrong password");
			req.setAttribute("errorPassword", "true");
		}
		
		if (isNameCorrect == true && isPasswordCorrect == true) {
			logger.trace("User login credentials are correct");
			req.setAttribute("user", returnedUser);
			
			HttpSession session = req.getSession();
			session.setAttribute("isLogin", "true");
			session.setAttribute("user", returnedUser.getName());
			
			Cookie userName = new Cookie("user", returnedUser.getName());
			resp.addCookie(userName);
			
			req.getRequestDispatcher("./index.jsp").forward(req, resp);
			
		} else {
			logger.trace("User login credentials are incorrect");
			req.getRequestDispatcher("./index.jsp").forward(req, resp);
		}
		
		

	}

}
