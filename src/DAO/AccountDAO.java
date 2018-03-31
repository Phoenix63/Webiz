package DAO;

import java.util.List;

import org.sql2o.Connection;

import Component.*;

public class AccountDAO {

	private static final String getAllQuery = "SELECT * FROM ACCOUNT;";
	private static final String getByNameQuery = "SELECT * FROM ACCOUNT WHERE username=:username;";
	
	private static final String insertQuery = 
			"INSERT INTO ACCOUNT (username, password, mail) VALUES (:username, :password, :mail);";

	private static final String updateQuery = 
			"UPDATE ACCOUNT SET username=:username, password=:password, mail=:mail WHERE id=:id;";
	
	private static final String deleteQuery = "DELETE FROM ACCOUNT WHERE id=:id";
	
	public static List<Account> getAll() throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			List<Account> result = conn.createQuery(getAllQuery)
					   					.executeAndFetch(Account.class);
			conn.close();
			
			return result;
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static Account getByName(String username) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			Account result = conn.createQuery(getByNameQuery)
								  .addParameter("username", username)
								  .executeAndFetchFirst(Account.class);
			conn.close();		
	
			return result;			
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static void create(Account account) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			int id = (int) conn.createQuery(insertQuery)
							   .addParameter("username", account.getUsername())
							   .addParameter("password", account.getPassword())
							   .addParameter("mail", account.getMail())
							   .executeUpdate()
							   .getKey();
			conn.commit();
			conn.close();
			
			account.setId(id);
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static void update(Account account) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {	
			conn.createQuery(updateQuery)
			    .addParameter("username", account.getUsername())
			    .addParameter("password", account.getPassword())
			    .addParameter("mail", account.getMail())
					.executeUpdate();
			conn.commit();
			conn.close();
		} else {
			throw new Exception("Database connection failled !");
		}		
	
	}
	
	public static void delete(Account account) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {	
			conn.createQuery(deleteQuery)
					.addParameter("id", account.getId())
					.executeUpdate();
			conn.commit();
			conn.close();
		} else {
			throw new Exception("Database connection failled !");
		}		
		
	}
	
}
