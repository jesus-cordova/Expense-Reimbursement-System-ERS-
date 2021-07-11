package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.DBConnection;

public class App {

	public static void main(String[] args) throws SQLException {
		
		
		
		try(Connection con = new DBConnection().getConnection())
		{
			//String query = "select * from dbo.Employees";
			String callablequery  = "{call dbo.DecryptPassword()}";
			CallableStatement statement = con.prepareCall(callablequery);
			//PreparedStatement ps  = con.prepareStatement(query);
			//ResultSet rs  = ps.executeQuery();
			ResultSet spr  = statement.executeQuery();
			
			
			
			
			while(spr.next())
			{
				System.out.println(spr.getString("Decrypted_Password"));
			}
		}

	}

}
