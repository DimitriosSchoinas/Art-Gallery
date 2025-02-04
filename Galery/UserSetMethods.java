package Galery;

import java.io.Serializable;

interface UserSetMethods extends User, Serializable{
	
	/**
	 * adds a bid to the active bids list
	 * 
	 * @param bid user bid
	 */
	void addActiveBid(Bid bid);

	/**
	 * removes a bid of the active bids list
	 * 
	 * @param bid user bid
	 */
	void removeActiveBid(Bid bid);

	/**
	 * verifies if the are any active bids
	 * 
	 * @return true if there are any active bids and false if not
	 */
	boolean hasAnyActiveBids();
}
