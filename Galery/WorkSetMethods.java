package Galery;

import java.io.Serializable;

interface WorkSetMethods extends Work, Serializable {

	/**
	 * sets the highest selling price
	 * 
	 * @param price price that we want to set
	 */
	void setHighestSellingPrice(int price);

	/**
	 * sets the last winning bid
	 * 
	 * @param price price that we want to set
	 */
	void setLastWinningBid(Bid bid);

}
