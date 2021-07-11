package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import connections.DBConnection;
import models.Employee;
import models.Reimbursement;

public class ManagerImplementation implements ManagerService {

	final static Logger logger = Logger.getLogger(ManagerImplementation.class);

	/**
	 * Calls a stored procedure that decrypts all employees password then checks to
	 * see if email and password match an employee
	 * 
	 * 
	 * @param email
	 * @param password
	 * 
	 * @return manager or null if not found
	 */
	@Override
	public Employee validateManagerLogin(String email, String password) throws SQLException {
		try (Connection con = new DBConnection().getConnection()) {
			String callablequery = "{call dbo.DecryptPassword()}";
			CallableStatement statement = con.prepareCall(callablequery);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				if (rs.getString("Email").equals(email) && rs.getString("Decrypted_Password").equals(password)
						&& rs.getInt("Manager_ID") == 0) {
					Employee currentMngr = new Employee(rs.getInt("Emp_ID"), rs.getString("First_Name"),
							rs.getString("Last_Name"), rs.getString("Email"), rs.getString("Phone_Number"));
					return currentMngr;
				}

			}
		}
		return null;
	}

	/**
	 * Joining reimbursement and employee tables to find the manager's employees
	 * then looks for all pending reimbursements
	 * 
	 * 
	 * @param manager ID
	 * 
	 * @return list of all the manager employee's reimbursements
	 */
	@Override
	public List<Reimbursement> reimbursementAction(int mngrID) throws SQLException {
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select reimb.Reimbursement_ID,  emp.First_Name ,  emp.Last_Name, reimb.Description, reimb.Amount, reimb.Status, reimb.Issue_Date\r\n"
					+ "from dbo.Employees emp \r\n"
					+ "inner join dbo.Employees mngr on emp.Manager_ID = mngr.Emp_ID\r\n"
					+ "inner join dbo.Reimbursements reimb on reimb.Emp_ID = emp.Emp_ID\r\n"
					+ "where mngr.Emp_ID = ? and reimb.Status = 'Pending';";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, mngrID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbID = rs.getInt("Reimbursement_ID");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String description = rs.getString("Description");
				Double amount = rs.getDouble("Amount");
				String status = rs.getString("Status");
				String date = rs.getString("Issue_Date");
				Reimbursement currentReimbursement = new Reimbursement(reimbID, fName, lName, description, amount,
						status, date);
				pendingReimbursements.add(currentReimbursement);

			}
		}
		return pendingReimbursements;
	}

	/**
	 * Perform an update to the reimbursement status when the manager has chosen accept
	 * or deny and give a completion date 
	 * 
	 * 
	 * @param reimbursement ID
	 * @param action
	 * @param completion date
	 */
	@Override
	public void updateReimbursementAction(int reimbID, String action, String date) throws SQLException {
		try (Connection con = new DBConnection().getConnection()) {

			String query = "update dbo.Reimbursements\r\n" + "set  Status=?, Complete_Date =?\r\n"
					+ "where Reimbursement_ID =?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, action);
			ps.setString(2, date);
			ps.setInt(3, reimbID);
			ps.executeUpdate();
			logger.warn("You have successully update the reimbursement table");

		}
	}

	/**
	 * Finds all the reimbursements that are pending
	 * 
	 * 
	 * @return list of all pending request
	 */
	@Override
	public List<Reimbursement> viewAllEmployeesPendingRequest() throws SQLException {
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select  reimbs.Reimbursement_ID, emps.First_Name, emps.Last_Name, reimbs.Description, reimbs.Amount, reimbs.Status , reimbs.Issue_Date \r\n"
					+ "from dbo.Employees emps\r\n"
					+ "inner join dbo.Reimbursements reimbs on emps.Emp_ID = reimbs.Emp_ID\r\n"
					+ "where reimbs.Status = 'Pending'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbID = rs.getInt("Reimbursement_ID");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String description = rs.getString("Description");
				Double amount = rs.getDouble("Amount");
				String status = rs.getString("Status");
				String date = rs.getString("Issue_Date");
				Reimbursement currentReimbursement = new Reimbursement(reimbID, fName, lName, description, amount,
						status, date);
				pendingReimbursements.add(currentReimbursement);

			}
		}
		return pendingReimbursements;
	}

	/**
	 * Finds all the reimbursements that are completed and displays their manager
	 * that completed it
	 * 
	 * 
	 * @return list of all completed reimbursements
	 */
	@Override
	public List<Reimbursement> viewAllEmployeesCompletedRequest() throws SQLException {
		List<Reimbursement> completedReimbursements = new ArrayList<Reimbursement>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select reimb.Reimbursement_ID, emp.First_Name + ' ' + emp.Last_Name as Employee,  mngr.First_Name + ' ' + mngr.Last_Name as Manager, reimb.Description, reimb.Status, reimb.Amount, reimb.Issue_Date, reimb.Complete_Date\r\n"
					+ "from dbo.Employees emp \r\n"
					+ "inner join dbo.Employees mngr on emp.Manager_ID = mngr.Emp_ID\r\n"
					+ "inner join dbo.Reimbursements reimb on reimb.Emp_ID = emp.Emp_ID\r\n"
					+ "where reimb.status != 'Pending'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbID = rs.getInt("Reimbursement_ID");
				String empFullName = rs.getString("Employee");
				String mngrFullName = rs.getString("Manager");
				String description = rs.getString("Description");
				Double amount = rs.getDouble("Amount");
				String status = rs.getString("Status");
				String issueDate = rs.getString("Issue_Date");
				String completeDate = rs.getString("Complete_Date");
				Reimbursement currentReimbursement = new Reimbursement(reimbID, empFullName, mngrFullName, description,
						amount, status, issueDate, completeDate);
				completedReimbursements.add(currentReimbursement);

			}
		}
		return completedReimbursements;
	}

	/**
	 * Finds all the employees but not managers
	 * 
	 * 
	 * @return list of all employees
	 */
	@Override
	public List<Employee> viewAllEmployees() throws SQLException {
		List<Employee> allEmployees = new ArrayList<Employee>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select emps.Emp_ID, emps.First_Name, emps.Last_Name, emps.Email, emps.Phone_Number\r\n"
					+ "from dbo.Employees emps \r\n" + "where emps.Manager_ID is not NULL;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int empID = rs.getInt("Emp_ID");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String email = rs.getString("Email");
				String phone = rs.getString("Phone_Number");
				Employee currentEmployee = new Employee(empID, fName, lName, email, phone);
				allEmployees.add(currentEmployee);

			}
		}
		return allEmployees;
	}

	/**
	 * Finds the id and name of all employees but no managers useful to populate
	 * dropdown
	 * 
	 * 
	 * @return list of all employees
	 */
	@Override
	public List<Employee> populateDropDown() throws SQLException {
		List<Employee> allEmployees = new ArrayList<Employee>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select emps.Emp_ID, emps.First_Name + ' ' + emps.Last_Name as Employee\r\n"
					+ "from dbo.Employees emps \r\n" + "where emps.Manager_ID is not NULL;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int empID = rs.getInt("Emp_ID");
				String fullName = rs.getString("Employee");
				Employee currentEmployee = new Employee(empID, fullName);
				allEmployees.add(currentEmployee);

			}
		}
		return allEmployees;
	}

	/**
	 * Finds all reimbursement requests for the employee that was chosen
	 * 
	 * 
	 * @return list of employee reimbursements
	 */

	@Override
	public List<Reimbursement> viewEmployeeReimbursementRequest(int empID) throws SQLException {
		List<Reimbursement> employeeReimbursements = new ArrayList<Reimbursement>();
		try (Connection con = new DBConnection().getConnection()) {

			String query = "select reimb.Reimbursement_ID, reimb.Description, reimb.Amount, reimb.Status, reimb.Issue_Date, reimb.Complete_Date\r\n"
					+ "from dbo.Reimbursements reimb\r\n" + "where reimb.Emp_ID =?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, empID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbID = rs.getInt("Reimbursement_ID");
				String description = rs.getString("Description");
				Double amount = rs.getDouble("Amount");
				String status = rs.getString("Status");
				String issueDate = rs.getString("Issue_Date");
				String completeDate = rs.getString("Complete_Date");

				Reimbursement currentReimbursement = new Reimbursement(reimbID, description, amount, status, issueDate,
						completeDate);
				employeeReimbursements.add(currentReimbursement);

			}
		}
		return employeeReimbursements;

	}

}
