package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import services.EmployeeImplementation;
import com.google.gson.*;

import models.Reimbursement;

@WebServlet("/employee/settings")
public class EmployeeGetSettings extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		EmployeeImplementation emp = new EmployeeImplementation();
		int empID = Integer.parseInt(req.getParameter("id"));
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();

		try {
			String json = new Gson().toJson(emp.viewEmployeeSettings(empID));
			out.print(json);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
