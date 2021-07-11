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

@WebServlet("/employeereimbursement")
public class EmployeeReimbursement extends HttpServlet {

	final static Logger logger = Logger.getLogger(EmployeeReimbursement.class);
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/employeereimbursement.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		logger.info("You have enter the post method to submit a new reimbursement ");
		EmployeeImplementation emp = new EmployeeImplementation();
		int empID = Integer.parseInt(req.getParameter("empID"));
		String description = req.getParameter("description");
		double  amount = Double.parseDouble(req.getParameter("amount"));
		String status = req.getParameter("status");
		String issueDate = req.getParameter("issueDate");
		try {
			emp.submitReimbursement(empID, description, amount, status, issueDate);
		} catch (SQLException e) {
			logger.error("Error when querying to make a new reimbursement" + e.getMessage());
		}
	}
}
