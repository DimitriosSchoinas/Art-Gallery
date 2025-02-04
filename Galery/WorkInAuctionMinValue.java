package Galery;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles the information
 *         about the Minimum value of the Work in an Auction
 */
public interface WorkInAuctionMinValue {
	/**
	 * gets the work of the auction
	 * 
	 * @return the work of the auction
	 */
	Work getWorkInAuction();

	/**
	 * gets the value of the work
	 * 
	 * @return the value of the work
	 */
	int getValue();
}
