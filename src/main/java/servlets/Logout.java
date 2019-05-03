package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logout extends HttpServlet {
	Logger logger = LogManager.getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		
		Cookie userNameCookieRemove = new Cookie("user", "");
		userNameCookieRemove.setMaxAge(0);
		resp.addCookie(userNameCookieRemove);
		
		req.getRequestDispatcher("./").forward(req, resp);
		
	}
	
}
