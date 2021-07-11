package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import models.Employee;
import services.EmployeeImplementation;
import services.ManagerImplementation;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(LoginServlet.class);

	EmployeeImplementation emp = new EmployeeImplementation();
	ManagerImplementation mngr = new ManagerImplementation();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);

	}
	/**
	 * Receives the values inside the email and password and checks to see if the employee
	 * or the manager exist
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		logger.info("Entering the login post method");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			Employee currentEmp = emp.validateEmloyeeLogin(email, password);
			Employee currentMngr  = mngr.validateManagerLogin(email, password);
			if (currentEmp != null) {
				HttpSession session = req.getSession();
				session.setAttribute("employee", currentEmp);
				res.sendRedirect("http://localhost:8080/Project1/employeedashboard");
				logger.info("An employee has successully logged in");

			}
			else if (currentMngr != null)
			{
				HttpSession session = req.getSession();
				session.setAttribute("manager", currentMngr);
				res.sendRedirect("http://localhost:8080/Project1/managerdashboard");
				logger.info("A manager has successully logged in");
			}
			else {
				req.setAttribute("error", "There is no employe with those credentials in our system");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
			}
		} catch (SQLException e) {
				logger.error("Error while trying query the database for a login " + e.getMessage());
		}

	}

}
