import DAO.*;

import Component.*; 

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {			
			UserList userList = new UserList("OS to install", "101010");
			userList.makePersistent();
			
			userList.createNewItem("Linux", "Installer");
			userList.createNewItem("Windows", "Installer");
		
			System.out.println(userList);
			
			for (Item item : ItemDAO.getByListId(userList.getId())) {
				System.out.println(item);
			}			
			
		} catch (Exception e) {
			System.out.println("ERR: " + e.getMessage());
		}
		
	}

}
