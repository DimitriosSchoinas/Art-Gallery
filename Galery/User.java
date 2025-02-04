package Galery;

import java.io.Serializable;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles information
 *         about a User
 */
public interface User extends Serializable {

	/**
	 * gets the user name
	 * 
	 * @return the user name
	 */
	String getUserName();

	/**
	 * gets the user age
	 * 
	 * @return the user age
	 */
	int getAge();

	/**
	 * gets the user email
	 * 
	 * @return the user email
	 */
	String getEmail();

	/**
	 * gets the user login
	 * 
	 * @return the user login
	 */
	String getLogin();
}
