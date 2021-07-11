package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/signout")
public class SignoutServlet extends HttpServlet {

	final static Logger logger = Logger.getLogger(SignoutServlet.class);
	
	/**
	 * Invalidates the session and redirects back to the login page
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession session  = req.getSession(false);
		if (session != null )
			session.invalidate();
		logger.info("User has successfully signed out ");
		res.sendRedirect("./login.jsp");
	}

	
}
