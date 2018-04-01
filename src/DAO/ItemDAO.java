package DAO;

import java.util.List;

import org.sql2o.Connection;

import Component.Item; 

public class ItemDAO {

	private static final String getAllQuery = "SELECT * FROM ITEM;";
	private static final String getByIdQuery = "SELECT * FROM ITEM WHERE ID=:id;";
	private static final String getByListIdQuery = "SELECT * FROM ITEM WHERE LIST_ID=:id;";
	private static final String getByIdAndListIdQuery = "SELECT * FROM ITEM WHERE ID=:id AND LIST_ID=:listId;";
	
	private static final String insertQuery = 
			"INSERT INTO ITEM (creation, last_modification, title, description, list_id, state) "
		  + "VALUES (:creation, :last_modification, :title, :description, :list_id, :state);";
	
	private static final String updateQuery = 
			"UPDATE ITEM SET creation=:creation, last_modification=:last_modification, title=:title, "
			+ "description=:description, list_id=:list_id, state=:state WHERE id=:id";
	
	private static final String deleteQuery = "DELETE FROM ITEM WHERE id=:id";
	
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
	
	public static Item getById(String id) throws Exception {
		
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
	
	public static List<Item> getByListId(String id) throws Exception {
		
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
	
	public static Item getByIdAndListId(String id, String listId) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			Item result = conn.createQuery(getByIdAndListIdQuery)
									.addParameter("id", id)
									.addParameter("listId", listId)
									.executeAndFetchFirst(Item.class);
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
								 .addParameter("state", item.getState())
							   .executeUpdate()
							   .getKey();
			conn.commit();
			conn.close();
			
			item.setId(id);
		} else {
			throw new Exception("Database connection failled !");
		}
		
	}
	
	public static void update(Item item) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {	
			conn.createQuery(updateQuery)
					.addParameter("creation", item.getCreationDate())
					.addParameter("last_modification", item.getLastModificationDate())
					.addParameter("title", item.getTitle())
					.addParameter("description", item.getDescription())
					.addParameter("list_id", item.getListId())
					.addParameter("state", item.getState())
					.addParameter("id", item.getId())
					.executeUpdate();
			conn.commit();
			conn.close();
		} else {
			throw new Exception("Database connection failled !");
		}		
	
	}
	
	public static void delete(Item item) throws Exception {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {	
			conn.createQuery(deleteQuery)
					.addParameter("id", item.getId())
					.executeUpdate();
			conn.commit();
			conn.close();
		} else {
			throw new Exception("Database connection failled !");
		}		
		
	}
	
}
