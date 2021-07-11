package managerservlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import models.Employee;
import services.ManagerImplementation;

@WebServlet("/manageraction")
public class ManagerAction extends HttpServlet {
	final static Logger logger = Logger.getLogger(ManagerAction.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/manageraction.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		logger.info("You have enter the post method to deny or accept a reimbursement");
		ManagerImplementation mngr = new ManagerImplementation();
		int reimbID = Integer.parseInt(req.getParameter("id"));
		String action = req.getParameter("action");
		String date = req.getParameter("date");
		try {
			mngr.updateReimbursementAction(reimbID, action, date);
				res.sendRedirect("http://localhost:8080/Project1/manageraction");
		} catch (SQLException e) {
			logger.error("Error when querying to  deny or accept a reimbursement" + e.getMessage());
		}
	}
}
