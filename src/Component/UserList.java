package Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import DAO.ListDAO;

/**
 * 
 * @author rolabussie
 *
 */
public class UserList implements Serializable {

	private static final long serialVersionUID = -1225600769043022066L;
	
	/// Primary key
	private int id = -1;
	
	private String title;
	private String description;
	
	private List<Item> itemList_;
	
	/**
	 * 
	 */
	public UserList() {
		itemList_ = new ArrayList<Item>();
	}
	
	/**
	 * 
	 * @param title
	 */
	public UserList(String title) {
		this();
		this.title = title;
	}
	
	/**
	 * 
	 * @param title
	 * @param description
	 */
	public UserList(String title, String description) {
		this(title);
		this.description = description;
	}
	
	/**
	 * 
	 */
	@Override 
	public String toString() {
		return "UserList@"+this.id
				+" ["+this.title+"] {"+this.description+"}"
				+" contains "+this.itemList_.size()+" item(s)";
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return The title of the current list
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return The description of the current list
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void setId(int id) throws Exception {
		if (this.id == -1) this.id = id;
		else throw new Exception("Already initialized property 'id'");
	}
	
	/**
	 * @return The current items
	 */
	public List<Item> getItemList() {
		return itemList_;
	}

	/**
	 * Define the title of the current list
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Define the description of the current list
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Define the items list of the current list
	 * @param itemList The item list to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList_ = itemList;
	}
	
	/**
	 * 
	 * @return
	 */
	public Item createNewItem() {
		if (this.id == -1) makePersistent();
		
		Item i = new Item();
		i.setListId(this.id);
		i.makePersistent();
		
		addItemToList(i);
		
		return i;
	}
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	public Item createNewItem(String title) {
		if (this.id == -1) makePersistent();
		
		Item i = new Item(title);
		i.setListId(this.id);
		i.makePersistent();
		
		addItemToList(i);
		
		return i;
	}
	
	/**
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	public Item createNewItem(String title, String description) {
		if (this.id == -1) makePersistent();
		
		Item i = new Item(title, description);
		i.setListId(this.id);
		i.makePersistent();
		
		addItemToList(i);
		
		return i;
	}
	
	/**
	 * Add the item to the current list
	 * @param item The item to add
	 */
	public void addItemToList(Item item) {
		this.itemList_.add(item);
	}
	
	/**
	 * 
	 */
	public void makePersistent() {

		try {
			if (this.id == -1) {
				ListDAO.create(this);
			} else {
				ListDAO.update(this);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
}
