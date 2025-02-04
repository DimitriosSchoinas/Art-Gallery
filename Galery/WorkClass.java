package Galery;

import java.io.Serializable;

class WorkClass implements Work, Serializable, WorkSetMethods {

	private static final int INITIAL_VALUE = 0;

	private static final long serialVersionUID = 1L;
	private String idWork;
	private String loginArtist;
	private int year;
	private String name;
	private Bid lastWinningBid;
	private int highestSellingPrice;
	private String artistName;

	WorkClass(String idWork, String loginArtist, int year, String name, String artistName) {

		this.artistName = artistName;
		this.idWork = idWork;
		this.loginArtist = loginArtist;
		this.year = year;
		this.name = name;
		this.lastWinningBid = null;
		this.highestSellingPrice = INITIAL_VALUE;
	}

	@Override
	public String getWorkName() {

		return name;
	}

	@Override
	public int getCreationYear() {

		return year;
	}

	@Override
	public String getLoginArtist() {

		return loginArtist;
	}

	@Override
	public String getWorkId() {

		return idWork;
	}

	@Override
	public String getArtistName() {
		return this.artistName;
	}

	@Override
	public void setHighestSellingPrice(int price) {
		if (this.highestSellingPrice < price)
			this.highestSellingPrice = price;
	}

	@Override
	public int getHighestSellingPrice() {
		return this.highestSellingPrice;
	}

	@Override
	public Bid getLastWinningBid() {
		return this.lastWinningBid;
	}

	@Override
	public void setLastWinningBid(Bid bid) {
		this.lastWinningBid = bid;
	}

	@Override
	public int compareTo(Work o) {
		int result = o.getHighestSellingPrice() - this.highestSellingPrice;
		if (result != 0) {
			return result;
		} else {
			result = name.compareTo(o.getWorkName());
			return result;
		}
	}
}
