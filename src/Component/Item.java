package Component;

import java.io.Serializable;
import java.util.Date;

import DAO.ItemDAO;

/**
 * 
 * @author rolabussie
 *
 */
public class Item implements Serializable {

	private static final long serialVersionUID = -7438596871526326674L;

	/// Primary key
	private int id = -1;
		
	private Date creation;
	private Date last_modification;
	private String title;
	private String description;
	
	private String state;
		
	/// Foreign key
	private int list_id;
	
	/**
	 * 
	 */
	public Item() {
		creation = new Date();
		last_modification = (Date) creation.clone();
		state = "TODO";
	}
	
	/**
	 * 
	 * @param title
	 */
	public Item(String title) {
		this();
		this.title = title;
	}
	
	/**
	 * 
	 * @param title
	 * @param description
	 */
	public Item(String title, String description) {
		this(title);
		this.description = description;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Item@"+this.id
				+" ["+this.title+"]" + "{"+this.description+"}"
				+" created: "+this.creation
				+" last updated: "+this.last_modification
				+" is "+this.state;
	}
	
	/**
	 * 
	 * @return The id of the current item
	 */
	public int getId() {
		return id;
	}
		
	/**
	 * @return The creationDate
	 */
	public Date getCreationDate() {
		return creation;
	}

	/**
	 * @return The last modification date
	 */
	public Date getLastModificationDate() {
		return last_modification;
	}

	/**
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return The id of the list owner
	 */
	public int getListId() {
		return list_id ;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * 
	 * @param id The id of the current item
	 * @throws Exception if the property id is already set
	 */
	public void setId(int id) throws Exception {
		if (this.id == -1) this.id = id;
		else throw new Exception("Already initialized property 'id'");
	}
	
	/**
	 * Define the creation date of the current item
	 * @param creationDate The creation date to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creation = creationDate;
	}

	/**
	 * Define the last modification date of the current item
	 * @param lastModificationDate The last modification date to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.last_modification = lastModificationDate;
	}

	/**
	 * Define the title of the current item, it will automatically set the modification date to now
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Define the description of the current item, it will automatically set the modification date to now
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @param id The id of the list owner
	 */
	public void setListId(int id) {
		this.list_id = id;
	}
	
	/**
	 * 
	 * @param state The state of the item, it must be "TODO", "DONE" or "DELETED"
	 */
	public void setState(String state) {
		if (state.equals("TODO") || 
				state.equals("DONE") || 
				state.equals("DELETED")) 
		{
			this.state = state;
		}
	}
	
	/**
	 * Make persistent the current item into the database
	 */
	public void makePersistent() {

		try {
			if (this.id == -1) {
				ItemDAO.create(this);
			}	else {
				ItemDAO.update(this);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
