package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {

	final static Logger logger = Logger.getLogger(DBConnection.class);
	static {
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			logger.error("Error while trying  to specify the sql driver " + e.getMessage());
		}
	}
	
	
	public Connection getConnection() throws SQLException
	{
		
			String url = "jdbc:sqlserver://localhost:1433;databasename=ERS;integratedSecurity=true";
			return DriverManager.getConnection(url);
	}
}
