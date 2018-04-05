package Webiz.UnitTest;

import static org.junit.Assert.*;
import org.junit.Test;

import Webiz.Component.*;

public class TestUserList {

	@Test
	public void testSetTitle() {
		
		UserList userList = new UserList();
		userList.setTitle("Linux");	
		
		assertEquals(
				"The title was not set correctly", 
				userList.getTitle(), 
				"Linux"
		);
		
	}

	@Test
	public void testSetDescription() {
		
		UserList userList = new UserList();
		userList.setDescription("Linux");
		
		assertEquals(
				"The description was not set correctly", 
				userList.getDescription(), 
				"Linux"
		);
		
	}

	@Test
	public void testAddItemToList() {
		
		UserList userList = new UserList();
		Item myItem = new Item();
		
		userList.addItemToList(myItem);
		
		assertEquals(
				"The item was not added correctly",
				userList.getItemList().size(),
				1
		);
		
	}

}
