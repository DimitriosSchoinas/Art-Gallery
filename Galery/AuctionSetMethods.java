package Galery;

import java.io.Serializable;

interface AuctionSetMethods extends Auction, Serializable {

	/**
	 * adds a work to the auction
	 * 
	 * @param work,     work to be added
	 * @param minValue, minimum value associated to that work in this auction
	 */
	void addWorkToAuction(Work work, int minValue);

	/**
	 * adds a bid to the auction
	 * 
	 * @param bid bid to add
	 */
	void addBid(String workID, Bid bid);

	/**
	 * Runs through the works inside the auction, checking for each every bid that
	 * has been placed on them, and updating the bid that each work was sold by, if
	 * they weren't sold nothing will happen
	 */
	void closeAuction();

	/**
	 * verifies if the auction has any works
	 * 
	 * @return true if the auction has any works and false if not
	 */
	boolean hasWorksInAuction();

	/**
	 * verifies if the auction has the specified work
	 * 
	 * @param work work that we want to find
	 * @return true if the auction has the specified work and false if not
	 */
	boolean hasWork(Work work);

	/**
	 * verifies if the specified work has any bids
	 * 
	 * @param work work that we want to select
	 * @return true if the specified work has any bids and false if not
	 */
	boolean doesWorkHaveBids(Work work);
}
