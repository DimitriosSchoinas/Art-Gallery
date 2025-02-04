package Galery;

import java.io.Serializable;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles the information
 *         about a Bid
 */
public interface Bid extends Serializable {
	/**
	 * gets the value of the bid
	 * 
	 * @return the value of the bid
	 */
	int getAmountOfBid();

	/**
	 * gets the user of the bid
	 * 
	 * @return the user of the bid
	 */
	User getUserBid();
}
