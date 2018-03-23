package DAO;

import java.util.List;

import org.sql2o.Connection;

import Component.Item; 

public class ItemDAO {

	private static final String getAllQuery = "SELECT * FROM ITEM;";
	private static final String getByIdQuery = "SELECT * FROM ITEM WHERE ID=:id;";
	private static final String getByListIdQuery = "SELECT * FROM ITEM WHERE LIST_ID=:id;";
	
	private static final String insertQuery = 
			"INSERT INTO ITEM (creation, last_modification, title, description, list_id) "
		  + "VALUES (:creation, :last_modification, :title, :description, :list_id);";
	
	public static List<Item> getAll() throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			List<Item> result = conn.createQuery(getAllQuery)
					   				.executeAndFetch(Item.class);
			conn.close();
			
			return result;
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static Item getById(int id) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			Item result = conn.createQuery(getByIdQuery)
					   		  .addParameter("id", id)
					   		  .executeAndFetchFirst(Item.class);
			conn.close();
			
			return result;			
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static List<Item> getByListId(int id) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			List<Item> result = conn.createQuery(getByListIdQuery)
									.addParameter("id", id)
									.executeAndFetch(Item.class);
			conn.close();
			
			return result;
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static void create(Item item) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			int id = (int) conn.createQuery(insertQuery)
							   .addParameter("creation", item.getCreationDate())
							   .addParameter("last_modification", item.getLastModificationDate())
							   .addParameter("title", item.getTitle())
							   .addParameter("description", item.getDescription())
							   .addParameter("list_id", item.getListId())
							   .executeUpdate()
							   .getKey();
			conn.commit();
			conn.close();
			
			item.setId(id);
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
}
