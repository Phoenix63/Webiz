package Webiz.DAO;

import org.sql2o.Connection;

public class AccountListDAO {

	private static final String insertQuery = 
			"INSERT INTO ACCOUNT_LIST (ACCOUNT_ID, LIST_ID) VALUES (:account, :list);";
	
	private static final String checkQuery = 
			"SELECT count(*) FROM ACCOUNT_LIST WHERE ACCOUNT_ID=:account AND LIST_ID=:list;";
	
	public static void insert(String account, String list) {

		Connection conn = DAO.getConnection();
		if (conn != null) {
			conn.createQuery(insertQuery)
					.addParameter("account", account)
		    	.addParameter("list", list)
				  .executeUpdate();
		}
		
	}
	
	public static boolean check(String account, String list) {
		
		Connection conn = DAO.getConnection();
		if (conn != null) {
			Integer count = conn.createQuery(checkQuery)
											.addParameter("account", account)
		    							.addParameter("list", list)
		    							.executeScalar(Integer.class);
			return count.intValue() == 1;
		}
		return false;
		
	}
	
}
