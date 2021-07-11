package services;

import java.sql.SQLException;
import java.util.List;

import models.Employee;
import models.Reimbursement;

public interface ManagerService{
	
	
	public Employee validateManagerLogin(String email, String password) throws SQLException;
	public List<Reimbursement> reimbursementAction(int mngrID) throws SQLException;
	public void updateReimbursementAction(int reimbID, String action, String date) throws SQLException;
	public List<Reimbursement> viewAllEmployeesPendingRequest() throws SQLException;
	public List<Reimbursement> viewAllEmployeesCompletedRequest() throws SQLException;
	public List<Employee> viewAllEmployees() throws SQLException;
	public List<Employee> populateDropDown() throws SQLException;	
	public List<Reimbursement>viewEmployeeReimbursementRequest(int empID) throws SQLException;
	

}
