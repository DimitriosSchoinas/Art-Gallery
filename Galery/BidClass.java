package Galery;

import java.io.Serializable;

class BidClass implements Bid, Serializable {

	private static final long serialVersionUID = 1L;
	private int amountOfBid;
	private User userBid;

	public BidClass(int amountOfBid, User userBid) {
		this.amountOfBid = amountOfBid;
		this.userBid = userBid;
	}

	@Override
	public int getAmountOfBid() {
		return this.amountOfBid;
	}

	@Override
	public User getUserBid() {
		return this.userBid;
	}

}
