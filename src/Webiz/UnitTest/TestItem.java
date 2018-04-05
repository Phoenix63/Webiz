package Webiz.UnitTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import Webiz.Component.*;

public class TestItem {
	
	/// Unit test on properties	
	@Test
	public void testSetCreationDate() {
		
		Item myItem = new Item();
		Date myDate = new Date();
		
		myItem.setCreationDate(myDate);	
		
		assertEquals(
				"The creation date was not set correctly", 
				myItem.getCreationDate(), 
				myDate
		);
		
	}

	
	@Test
	public void testSetLastModificationDate() {
		
		Item myItem = new Item();
		Date myDate = new Date();
		
		myItem.setLastModificationDate(myDate);	
		
		assertEquals(
				"The last modification date was not set correctly", 
				myItem.getLastModificationDate(), 
				myDate
		);
		
	}
		
	@Test
	public void testSetTitle() {
		
		Item myItem = new Item();
		myItem.setTitle("Linux");	
		
		assertEquals(
				"The title was not set correctly", 
				myItem.getTitle(), 
				"Linux"
		);
		
	}

	@Test
	public void testSetDescription() {
		
		Item myItem = new Item();
		myItem.setDescription("Linux");	
		
		assertEquals(
				"The description was not set correctly", 
				myItem.getDescription(), 
				"Linux"
		);
		
	}

	/// Unit test on DAO
	//create -> save -> get -> compare
	//get -> update -> save -> get -> compare
	
}
