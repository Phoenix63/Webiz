import Utils.TemplateConfiguration;

import DAO.*;
import Component.*;
import Controller.*;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/// Configure freeMarker
		TemplateConfiguration.initialize();
		
		/// Listen /list route
		ListController listCtrl = new ListController();
		
		/*
		try {			
			UserList userList = new UserList("OS to install", "101010");
			userList.makePersistent();

			userList.createNewItem("Linux", "Installer");
			userList.createNewItem("Windows", "Installer");

			System.out.println(userList);

			List<Item> itemList = ItemDAO.getByListId(userList.getId());	
			for (Item item : itemList) {
				System.out.println(item);
			}

		} catch (Exception e) {
			System.out.println("ERR: " + e.getMessage());
		}*/

	}

}
