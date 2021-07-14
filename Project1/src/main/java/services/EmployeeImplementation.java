package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import connections.DBConnection;
import models.Employee;
import models.Reimbursement;

public class EmployeeImplementation implements EmployeeService {

	final static Logger logger = Logger.getLogger(ManagerImplementation.class);

	/**
	 * Calls a stored procedure that decrypts all employees password then checks to
	 * see if email and password match a manager
	 * 
	 * 
	 * @param email
	 * @param password
	 * 
	 * @return manager or null if not found
	 */
	@Override
	public Employee validateEmloyeeLogin(String email, String password) throws SQLException {

		try (Connection con = new DBConnection().getConnection()) {
			String callablequery = "{call dbo.DecryptPassword()}";
			CallableStatement statement = con.prepareCall(callablequery);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				if (rs.getString("Email").equals(email) && rs.getString("Decrypted_Password").equals(password)
						&& rs.getInt("Manager_ID") != 0) {
					Employee currentEmp = new Employee(rs.getInt("Emp_ID"), rs.getString("First_Name"),
							rs.getString("Last_Name"), rs.getString("Email"), rs.getString("Phone_Number"));
					return currentEmp;
				}

			}
		}
		return null;
	}

	/**
	 * inserts a new reimbursement request to the database
	 * 
	 * 
	 * @param employee      ID
	 * @param reimbursement description
	 * @param reimbursement amount
	 * @param reimbursement status
	 * @param rimbursment   issuedate
	 */
	@Override
	public void submitReimbursement(int empID, String description, double amount, String status, String issueDate)
			throws SQLException {
		try (Connection con = new DBConnection().getConnection()) {

			String query = "INSERT INTO dbo.Reimbursements\r\n"
					+ "           (Emp_ID,Description,Amount,Status,Issue_Date)\r\n" + "     VALUES\r\n"
					+ "           (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, empID);
			ps.setString(2, description);
			ps.setDouble(3, amount);
			ps.setString(4, status);
			ps.setDate(5, Date.valueOf(issueDate));
			ps.executeUpdate();

		}
	}

	/**
	 * Finds all the employee's reimbursements that are pending
	 * 
	 * 
	 * 
	 * @param employee ID
	 * 
	 * @return list of an employee reimbursements
	 */
	@Override
	public List<Reimbursement> viewEmployeePendingReimursements(int empID) throws SQLException {

		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select reimbs.Reimbursement_ID , reimbs.Description, reimbs.Amount, reimbs.Status, reimbs.Issue_Date\r\n"
					+ "from dbo.Reimbursements reimbs\r\n"
					+ "inner join dbo.Employees emps on reimbs.Emp_ID = emps.Emp_ID\r\n"
					+ "where emps.Emp_ID = ? and reimbs.Status = 'Pending';\r\n" + "";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, empID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbID = rs.getInt("Reimbursement_ID");
				String description = rs.getString("Description");
				Double amount = rs.getDouble("Amount");
				String status = rs.getString("Status");
				String date = rs.getString("Issue_Date");
				Reimbursement currentReimbursement = new Reimbursement(reimbID, description, amount, status, date);
				pendingReimbursements.add(currentReimbursement);

			}
		}
		return pendingReimbursements;

	}

	/**
	 * Finds all the employee's reimbursements that are completed
	 * 
	 * 
	 * 
	 * @param employee ID
	 * 
	 * @return list of an employee reimbursements
	 */
	@Override
	public List<Reimbursement> viewEmployeeCompletedReimbursments(int empID) throws SQLException {
		List<Reimbursement> completedReimbursements = new ArrayList<Reimbursement>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select reimbs.Reimbursement_ID , reimbs.Description, reimbs.Amount, reimbs.Status, reimbs.Issue_Date, reimbs.Complete_Date\r\n"
					+ "from dbo.Reimbursements reimbs\r\n"
					+ "inner join dbo.Employees emps on reimbs.Emp_ID = emps.Emp_ID\r\n"
					+ "where emps.Emp_ID = ? and reimbs.Status != 'Pending';\r\n" + "";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, empID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbID = rs.getInt("Reimbursement_ID");
				String description = rs.getString("Description");
				Double amount = rs.getDouble("Amount");
				String status = rs.getString("Status");
				String issuedate = rs.getString("Issue_Date");
				String completedate = rs.getString("Complete_Date");
				Reimbursement currentReimbursement = new Reimbursement(reimbID, description, amount, status, issuedate, completedate);
				completedReimbursements.add(currentReimbursement);

			}
		}
		return completedReimbursements;

	}

	/**
	 * Finds the employee information
	 * 
	 * @param employee ID
	 * 
	 * @return an employee
	 */
	@Override
	public Employee viewEmployeeSettings(int empID) throws SQLException {

		try (Connection con = new DBConnection().getConnection()) {

			String query = "select Emp_ID, First_Name, Last_Name, Email, Phone_Number\r\n" + "from dbo.Employees\r\n"
					+ "where Emp_ID = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, empID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("Emp_ID");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String email = rs.getString("Email");
				String phone = rs.getString("Phone_Number");
				return new Employee(id, fName, lName, email, phone);
			}
		}
		return null;

	}

	/**
	 * updates the employee information
	 * 
	 * @param employee  new first Name
	 * @param employee  new last Name
	 * @param employeee new email
	 * @param employee  new phone
	 * @param empmloyee ID
	 * 
	 */
	@Override
	public void updateEmployeeInformation(String fName, String lName, String email, String phone, int empID)
			throws SQLException {

		try (Connection con = new DBConnection().getConnection()) {

			String query = "UPDATE dbo.Employees\r\n" + "SET First_Name = ?, Last_Name= ? , Email=?, Phone_Number=?\r\n"
					+ "WHERE Emp_ID= ?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setInt(5, empID);
			ps.executeUpdate();

		}
	}

}
