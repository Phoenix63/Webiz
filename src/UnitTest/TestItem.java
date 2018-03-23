package UnitTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import Component.*;

public class TestItem {
	
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
	public void testSetLastModificationDateWhenTitleChange() {
		
		Item myItem = new Item();
		Date myDate = myItem.getLastModificationDate();
		
		/// NOTE: Used because the run is too quickly
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myItem.setTitle("Linux");	
		
		assertNotEquals(
				"The last modification date was not changed correctly", 
				myItem.getLastModificationDate(), 
				myDate
		);
		
	}
	
	@Test
	public void testSetLastModificationDateWhenDescriptionChange() {
		
		Item myItem = new Item();
		Date myDate = myItem.getLastModificationDate();

		/// NOTE: Used because the run is too quickly
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myItem.setDescription("Linux");	
					
		assertNotEquals(
				"The last modification date was not changed correctly", 
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

}
