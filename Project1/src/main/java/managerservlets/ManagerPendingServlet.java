package managerservlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import services.ManagerImplementation;

@WebServlet("/managerpending")
public class ManagerPendingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/managerpending.jsp").forward(req, res);
	}

}
