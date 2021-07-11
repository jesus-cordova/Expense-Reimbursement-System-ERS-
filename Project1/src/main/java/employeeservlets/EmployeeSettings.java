package employeeservlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import models.Employee;
import services.EmployeeImplementation;

@WebServlet("/employeesettings")
public class EmployeeSettings extends HttpServlet {

	final static Logger logger = Logger.getLogger(EmployeeSettings.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/employeesettings.jsp").forward(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("You have enter the post method to change user settings ");
		EmployeeImplementation emp = new EmployeeImplementation();
		String fName = req.getParameter("fName");
		String lName = req.getParameter("lName");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		int empID = Integer.parseInt(req.getParameter("id"));
		try {
			emp.updateEmployeeInformation(fName, lName, email, phone, empID);
			getServletContext().getRequestDispatcher("/employeesettings.jsp").forward(req, res);
		} catch (SQLException e) {
			logger.error("Error when querying to change settings of an employee" + e.getMessage());
		}
	}
}
