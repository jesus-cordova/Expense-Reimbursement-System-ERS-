import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import models.Employee;
import models.Reimbursement;
import services.EmployeeImplementation;
import services.ManagerImplementation;

public class DatabaseTest {

	ManagerImplementation mngr = new ManagerImplementation();
	EmployeeImplementation emp = new EmployeeImplementation();

	@Test
	public void testManagerExist() throws SQLException {

		Employee  employee = mngr.validateManagerLogin("name@email.com", "name");
		assertEquals(employee, null);
	}
	@Test
	public void testEmployeeExist() throws SQLException {
		Employee  employee = emp.validateEmloyeeLogin("josh@email.com", "josh");
		assertEquals(employee.getFirstName(), "Josh");
	}
	@Test
	public void testEmployeeSettings() throws SQLException {
		Employee  employee = emp.viewEmployeeSettings(1);
		assertEquals(employee.getEmail(), "josh@email.com");
	}
	@Test
	public void testGetReimbursements() throws SQLException {
		List<Reimbursement> expectedReimbs  = new ArrayList<Reimbursement>();
		Reimbursement myReimb = new Reimbursement(16, "trip to floria", 200, "pending", "2021-07-08");
		expectedReimbs.add(myReimb);
		List<Reimbursement> reimbs  = emp.viewEmployeePendingReimursements(5);
		assertEquals(reimbs.get(0).getReimbursementID(), expectedReimbs.get(0).getReimbursementID());
	}
	@Test
	public void testGeEmployees() throws SQLException {
		List<Employee> expectedEmps = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "Josh Joshua");
		Employee emp2 = new Employee(2, "Matt Mathew");
		Employee emp3 = new Employee(3, "Dyl Dylan");
		Employee emp4 = new Employee(4, "Dave David");
		expectedEmps.add(emp1);
		expectedEmps.add(emp2);
		expectedEmps.add(emp3);
		expectedEmps.add(emp4);
		List<Employee> emps  = mngr.populateDropDown();
		assertEquals(emps.size(), expectedEmps.size());
	}
}
