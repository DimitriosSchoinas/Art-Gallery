package Galery;

import java.io.Serializable;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles information
 *         about a Work
 */
public interface Work extends Serializable, Comparable<Work> {

	/**
	 * gets the work name
	 * 
	 * @return the work name
	 */
	String getWorkName();

	/**
	 * gets the creation year of the work
	 * 
	 * @return the creation year of the work
	 */
	int getCreationYear();

	/**
	 * gets the artist login
	 * 
	 * @return the artist login
	 */
	String getLoginArtist();

	/**
	 * gets the work id
	 * 
	 * @return the work id
	 */
	String getWorkId();

	/**
	 * gets the name of the creator of the work
	 * 
	 * @return the name of the creator of the work
	 */
	String getArtistName();

	/**
	 * gets the highest selling price
	 * 
	 * @return the highest selling price
	 */
	int getHighestSellingPrice();

	/**
	 * returns last winning bid
	 * 
	 * @return last winning bid
	 */
	Bid getLastWinningBid();

}
