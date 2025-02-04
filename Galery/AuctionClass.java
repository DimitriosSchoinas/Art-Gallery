package Galery;

import dataStructures.*;
import java.io.Serializable;

class AuctionClass implements Auction, Serializable, AuctionSetMethods {

	private static final long serialVersionUID = 1L;
	private String auctionID;
	private Dictionary<String, Integer> worksInAuctionMinValue; // K- workID, V- minValue
	private Dictionary<String, List<Bid>> bids; // K- workId, V- list of bids
	private Dictionary<String, Work> works;// K- workID, V- works (has all works in this auction)

	public AuctionClass(String auctionID) {
		this.auctionID = auctionID;
		worksInAuctionMinValue = new SepChainHashTable<String, Integer>();
		bids = new SepChainHashTable<String, List<Bid>>();
		works = new SepChainHashTable<String, Work>();
	}

	@Override
	public String getAuctionID() {
		return auctionID;
	}

	@Override
	public void addWorkToAuction(Work work, int minValue) {
		if (works.find(work.getWorkId().toLowerCase()) == null) {
			this.works.insert(work.getWorkId().toLowerCase(), work);
			this.worksInAuctionMinValue.insert(work.getWorkId().toLowerCase(), minValue);
			this.bids.insert(work.getWorkId().toLowerCase(), null);
		}
	}

	@Override
	public void addBid(String workID, Bid bid) {
		List<Bid> list = this.bids.find(workID.toLowerCase());
		if (list == null) {
			list = new DoubleList<Bid>();
			bids.insert(workID.toLowerCase(), list);
		}
		list.addLast(bid);
	}

	@Override
	public void closeAuction() {
		Iterator<Entry<String, List<Bid>>> it = bids.iterator();
		User user = null;
		Bid bid = null;
		while (it.hasNext()) {
			Entry<String, List<Bid>> ent = it.next();
			List<Bid> list = ent.getValue();
			String workID = ent.getKey();
			Work work = works.find(workID.toLowerCase());
			if (list != null) {
				int max = 0;
				user = null;
				Iterator<Bid> itBid = list.iterator();
				while (itBid.hasNext()) {
					bid = itBid.next();
					user = bid.getUserBid();
					if (bid.getAmountOfBid() > max) {
						max = bid.getAmountOfBid();
					}
					((UserSetMethods) user).removeActiveBid(bid);
					if (user != null && max != 0 && bid.getAmountOfBid() == max) {
						((WorkSetMethods) work).setLastWinningBid(bid);
						((WorkSetMethods) work).setHighestSellingPrice(max);
					}
				}
			} else {
				((WorkSetMethods) work).setLastWinningBid(null);
			}
		}

	}

	@Override
	public boolean hasWorksInAuction() {
		return this.works.size() > 0;
	}

	@Override
	public boolean doesWorkHaveBids(Work work) {
		return bids.find(work.getWorkId().toLowerCase()) != null;
	}

	@Override
	public Iterator<Bid> listWorkBids(Work work) {
		List<Bid> tmp = bids.find(work.getWorkId());
		return tmp.iterator();
	}

	@Override
	public int getWorkMinValue(Work work) {
		return this.worksInAuctionMinValue.find(work.getWorkId());
	}

	@Override
	public boolean hasWork(Work work) {
		return works.find(work.getWorkId()) != null;
	}
}
