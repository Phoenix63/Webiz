package DAO;

import java.util.List;

import org.sql2o.Connection;

import Component.*;


public class ListDAO {

	private static final String getAllQuery = "SELECT * FROM LIST;";
	private static final String getByIdQuery = "SELECT * FROM LIST WHERE ID=:id;";
	
	private static final String insertQuery = 
			"INSERT INTO LIST (title, description)"
		  + "VALUES (:title, :description);";

	public static List<UserList> getAll() throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			List<UserList> result = conn.createQuery(getAllQuery)
					   					.executeAndFetch(UserList.class);
			conn.close();
			
			return result;
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static UserList getById(String id) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			UserList result = conn.createQuery(getByIdQuery)
								  .addParameter("id", id)
								  .executeAndFetchFirst(UserList.class);
			conn.close();			
			
			return result;			
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static void create(UserList userList) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			int id = (int) conn.createQuery(insertQuery)
							   .addParameter("title", userList.getTitle())
							   .addParameter("description", userList.getDescription())
							   .executeUpdate()
							   .getKey();
			conn.commit();
			conn.close();
			
			userList.setId(id);
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
}
