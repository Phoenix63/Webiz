package Webiz.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Secure {

	private static final String SALT = "Webiz";

	public static String hash(String password) {

		String passwordHash = null;

		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(SALT.getBytes());

			//Get the hash's bytes
			byte[] bytes = md.digest(password.getBytes());
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			passwordHash = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 

		return passwordHash;
	}

}
