package services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import models.Reimbursement;

public interface EmployeeService {

	public Employee validateEmloyeeLogin(String email, String password) throws SQLException;
	public void submitReimbursement(int empID, String description, double amount, String status, String issueDate ) throws SQLException;
	public List<Reimbursement> viewEmployeePendingReimursements(int empID) throws SQLException;
	public List<Reimbursement>	viewEmployeeCompletedReimbursments(int empID ) throws SQLException; 
	public Employee viewEmployeeSettings(int empID) throws SQLException;
	public void updateEmployeeInformation(String fName, String lName, String email, String phone , int empID) throws SQLException;
	
}
