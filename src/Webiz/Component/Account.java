package Webiz.Component;

import java.io.Serializable;

import Webiz.DAO.AccountDAO;

/**
 * 
 * @author rolabussie
 *
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 9075156163122018364L;

	/// Primary key
	private int id = -1;
	
	private String username;
	private String password;
	private String mail;
	
	public Account() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
		
	/**
	 * Make persistent the current account into the database
	 */
	public void makePersistent() {

		try {
			if (this.id == -1) {
				AccountDAO.create(this);
			}	else {
				AccountDAO.update(this);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
