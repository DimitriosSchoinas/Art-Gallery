package Galery;

import java.io.Serializable;

public class WorkInAuctionMinValueClass implements WorkInAuctionMinValue, Serializable {

	private static final long serialVersionUID = 1L;
	private Work workInAuction;
	private int minValue;

	public WorkInAuctionMinValueClass(Work work, int value) {
		this.workInAuction = work;
		this.minValue = value;
	}

	@Override
	public Work getWorkInAuction() {
		return this.workInAuction;
	}

	@Override
	public int getValue() {
		return this.minValue;
	}

}
