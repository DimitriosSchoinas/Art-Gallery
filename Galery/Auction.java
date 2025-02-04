package Galery;

import java.io.Serializable;
import dataStructures.*;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles the information
 *         about an Auction
 *
 */
public interface Auction extends Serializable {

	/**
	 * gets the auction id
	 * 
	 * @return the auction id
	 */
	String getAuctionID();

	/**
	 * lists the bids of a work
	 * 
	 * @param work work that we want to select
	 * @return the iterator that lists the bids of a work
	 */
	Iterator<Bid> listWorkBids(Work work);

	/**
	 * gets the minimum value of a work
	 * 
	 * @param work work that we want to select
	 * @return the minimum value of a work
	 */
	int getWorkMinValue(Work work);
}
