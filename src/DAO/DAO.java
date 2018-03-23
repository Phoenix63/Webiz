package DAO;

import org.sql2o.*;

public abstract class DAO {
	
	public static Sql2o sqlConnection = new Sql2o("jdbc:h2:~/Webiz", "Kay", "dop");
	
	public static Connection getConnection() { 

		Connection conn = null;
		try {
			conn = sqlConnection.open();
		} catch (Exception e) {
			System.out.println("ERR: " + e.getMessage());
		}
		return conn;
		
	}
	
}
