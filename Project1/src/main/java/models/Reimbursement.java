package models;

public class Reimbursement {

	private int reimbursementID;
	private String description;
	private double amount;
	private String status;
	private String issueDate;
	private String completedDate;
	private String fName;
	private String lName;
	private String empFullName;
	private String mngrFullName;

	//for getting requests for employee
	public Reimbursement(int reimbursementID, String description, double amount, String status, String issueDate) {
		this.reimbursementID = reimbursementID;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.issueDate = issueDate;
	}
	// this constructor is used to display all details of requests when choosing an employee
	public Reimbursement(int reimbursementID, String description, double amount, String status, String issueDate, String completedDate) 
	{
		this(reimbursementID, description , amount, status, issueDate);
		this.completedDate = completedDate;

	}
	// this constructor is used to retrieving employee infomration for the managers benefit when look at requests 
	public Reimbursement(int reimbursementID, String fName, String lName, String description, double amount, String status, String issueDate) 
	{
		this(reimbursementID, description , amount, status, issueDate);
		this.fName  = fName;
		this.lName  = lName;

	}
	//this constructor is used to retrieve the reimbursement with the employee name and manager name 
	public Reimbursement(int reimbursementID, String empFullName, String mngrFullName, String description, double amount, String status, String issueDate, String completedDate) 
	{
		this(reimbursementID, description , amount, status, issueDate);
		this.empFullName  = empFullName;
		this.mngrFullName = mngrFullName;
		this.completedDate = completedDate;

	}

	public int getReimbursementID() {
		return reimbursementID;
	}

	public void setReimbursementID(int reimbursementID) {
		this.reimbursementID = reimbursementID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementID=" + reimbursementID + ", description=" + description
				+ ", amount=" + amount + ", status=" + status + ", issueDate=" + issueDate + ", completedDate="
				+ completedDate + "]";
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	


}
