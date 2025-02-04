package Galery;

import java.io.*;

import Exceptions.*;
import dataStructures.*;

public class GaleryClass implements Galery, Serializable {

	private static final int MIN_AGE = 18;

	private static final long serialVersionUID = 1L;
	private Dictionary<String, User> users; // K- login, E-User(has all users)
	private Dictionary<String, Work> works; // K- workID, E-Work(has all works)
	private Dictionary<String, Auction> auctions; // K- auctionID, E- Auction (has all auctions)
	private Dictionary<String, OrderedDictionary<String, Work>> artistWorks; // K- login, V- BST (
	private Dictionary<String, List<Work>> auctionWorks;// K- auctionID, V-list of works in said auction
	private OrderedDictionary<Work, Work> listWorksByValue; // K- A work, V-the same work

	public GaleryClass() {
		users = new SepChainHashTable<String, User>();
		works = new SepChainHashTable<String, Work>();
		auctions = new SepChainHashTable<String, Auction>();
		artistWorks = new SepChainHashTable<String, OrderedDictionary<String, Work>>();
		auctionWorks = new SepChainHashTable<String, List<Work>>();
		this.listWorksByValue = new BinarySearchTree<Work, Work>();
	}

	@Override
	public void addUser(String login, String name, int age, String email)
			throws UnderageException, ExistentUserException {
		if (age < MIN_AGE)
			throw new UnderageException();
		if (users.size() > 0 && users.find(login.toLowerCase()) != null)
			throw new ExistentUserException();
		users.insert(login.toLowerCase(), new CollectorUserClass(login, name, age, email));
	}

	@Override
	public void addArtist(String login, String name, String artisticName, int age, String email)
			throws UnderageException, ExistentUserException {
		if (age < MIN_AGE)
			throw new UnderageException();
		if (users.size() > 0 && users.find(login.toLowerCase()) != null)
			throw new ExistentUserException();
		users.insert(login.toLowerCase(), new ArtistClass(login, name, artisticName, age, email));

	}

	private boolean hasAnyWorksInAuction(String login) {
		Iterator<Entry<String, List<Work>>> it = this.auctionWorks.iterator();
		while (it.hasNext()) {
			List<Work> list = it.next().getValue();
			Iterator<Work> itWork = list.iterator();
			while (itWork.hasNext()) {
				Work work = itWork.next();
				if (work.getLoginArtist().toLowerCase().equals(login.toLowerCase()))
					return true;
			}
		}
		return false;
	}

	@Override
	public void removeUser(String login)
			throws InexistentUserException, UserHasActiveBidsException, ArtistHasWorksInAuctionException {
		if (users.find(login.toLowerCase()) == null)
			throw new InexistentUserException();
		User user = users.find(login.toLowerCase());
		if (((UserSetMethods) user).hasAnyActiveBids())
			throw new UserHasActiveBidsException();
		if (user instanceof Artist && this.hasAnyWorksInAuction(login))
			throw new ArtistHasWorksInAuctionException();
		if (user instanceof Artist) {
			OrderedDictionary<String, Work> dic = artistWorks.find(login.toLowerCase());
			if (dic != null) {
				Iterator<Entry<String, Work>> it = dic.iterator();
				if (it != null) {
					while (it.hasNext()) {
						Work work = it.next().getValue();
						listWorksByValue.remove(works.find(work.getWorkId()));
						works.remove(work.getWorkId());
					}
				}
			}
			artistWorks.remove(login.toLowerCase());
		}
		users.remove(login.toLowerCase());
	}

	@Override
	public void addWork(String workId, String artistLogin, int year, String name)
			throws ExistentWorkException, InexistentUserException, InexistentArtistException {
		if (works.size() > 0 && works.find(workId.toLowerCase()) != null)
			throw new ExistentWorkException();
		User user = users.find(artistLogin.toLowerCase());
		if (user == null)
			throw new InexistentUserException();
		if (!(user instanceof Artist))
			throw new InexistentArtistException();
		Work work = new WorkClass(workId, artistLogin, year, name, user.getUserName());
		works.insert(workId.toLowerCase(), work);
		OrderedDictionary<String, Work> dic = artistWorks.find(artistLogin.toLowerCase());
		if (dic == null) {
			dic = new BinarySearchTree<String, Work>();
			artistWorks.insert(artistLogin.toLowerCase(), dic);
		}
		dic.insert(name, work);
	}	

	@Override
	public User getUser(String login) throws InexistentUserException {
		User user = users.find(login.toLowerCase());
		if (user == null)
			throw new InexistentUserException();
		return user;
	}

	@Override
	public Artist getArtist(String login) throws InexistentUserException, InexistentArtistException {
		User user = users.find(login.toLowerCase());
		if (user == null)
			throw new InexistentUserException();
		if (!(user instanceof Artist))
			throw new InexistentArtistException();
		return (Artist) user;
	}

	@Override
	public Work getWork(String idWork) throws InexistentWorkException {
		Work work = works.find(idWork.toLowerCase());
		if (work == null)
			throw new InexistentWorkException();
		return work;
	}

	@Override
	public void createAuction(String auctionId) throws ExistentAuctionException {
		if (auctions.size() > 0 && auctions.find(auctionId.toLowerCase()) != null)
			throw new ExistentAuctionException();
		auctions.insert(auctionId.toLowerCase(), new AuctionClass(auctionId));
	}

	@Override
	public void addWorkAuction(String auctionId, String workId, int minPrice)
			throws InexistentAuctionException, InexistentWorkException {
		Auction auction = auctions.find(auctionId.toLowerCase());
		if (auction == null)
			throw new InexistentAuctionException();
		Work work = works.find(workId.toLowerCase());
		if (work == null)
			throw new InexistentWorkException();
		List<Work> list = this.auctionWorks.find(auctionId.toLowerCase());
		if (list == null) {
			list = new DoubleList<Work>();
			this.auctionWorks.insert(auctionId.toLowerCase(), list);
		}
		if (list.find(work) == -1) {
			list.addLast(work);
		}
		((AuctionSetMethods) auction).addWorkToAuction(work, minPrice);
	}

	@Override
	public void bid(String auctionId, String workId, String login, int value) throws InexistentAuctionException,
			InexistentWorkInAuctionException, InexistentUserException, PriceUnderMinPriceException {
		User user = users.find(login.toLowerCase());
		if (user == null)
			throw new InexistentUserException();
		Auction auction = auctions.find(auctionId.toLowerCase());
		if (auction == null)
			throw new InexistentAuctionException();
		Work work = works.find(workId.toLowerCase());
		if (work == null)
			throw new InexistentWorkInAuctionException();
		if (!((AuctionSetMethods) auction).hasWork(work))
			throw new InexistentWorkInAuctionException();
		if (auction.getWorkMinValue(work) > value)
			throw new PriceUnderMinPriceException();
		Bid bid = new BidClass(value, user);
		((AuctionSetMethods) auction).addBid(workId, bid);
		((UserSetMethods) user).addActiveBid(bid);
	}

	@Override
	public Iterator<Work> listClosedAuction(String auctionId) throws InexistentAuctionException {
		Auction auction = auctions.find(auctionId.toLowerCase());
		if (auction == null) {
			throw new InexistentAuctionException();
		}
		List<Work> list = this.auctionWorks.find(auctionId.toLowerCase());
		if (list != null) {
			return list.iterator();
		} else
			return null;
	}

	@Override
	public Iterator<Work> listAuctionWorks(String auctionId)
			throws InexistentAuctionException, AuctionWithoutWorksException {
		Auction auction = auctions.find(auctionId.toLowerCase());
		if (auction == null)
			throw new InexistentAuctionException();
		if (!((AuctionSetMethods) auction).hasWorksInAuction())
			throw new AuctionWithoutWorksException();
		List<Work> list = this.auctionWorks.find(auctionId.toLowerCase());
		return list.iterator();
	}

	@Override
	public void closeAuction(String auctionId) throws InexistentAuctionException {
		Auction auction = auctions.find(auctionId.toLowerCase());
		if (auction == null)
			throw new InexistentAuctionException();
		List<Work> list = this.auctionWorks.find(auctionId.toLowerCase());
		if (list != null) {
			Iterator<Work> it = list.iterator();
			while (it.hasNext()) {
				Work work = it.next();
				if (this.listWorksByValue.find(work) != null)
					this.listWorksByValue.remove(work);
			}
		}
		((AuctionSetMethods) auction).closeAuction();
		if (list != null) {
			Iterator<Work> it = list.iterator();
			while (it.hasNext()) {
				Work work = it.next();
				if (work.getHighestSellingPrice() > 0)
					this.listWorksByValue.insert(work, work);
			}
		}
		auctions.remove(auctionId.toLowerCase());
		auctionWorks.remove(auctionId.toLowerCase());
	}

	@Override
	public Iterator<Entry<String, Work>> listArtistWorks(String login)
			throws InexistentUserException, InexistentArtistException, ArtistWithoutWorksException {
		User user = users.find(login.toLowerCase());
		if (user == null)
			throw new InexistentUserException();
		if (!(user instanceof Artist))
			throw new InexistentArtistException();
		OrderedDictionary<String, Work> dic = artistWorks.find(user.getLogin().toLowerCase());
		if (dic == null)
			throw new ArtistWithoutWorksException();
		return dic.iterator();
	}

	@Override
	public Iterator<Bid> listBidsWork(String auctionId, String workId)
			throws InexistentAuctionException, InexistentWorkInAuctionException, WorkWithoutBidsException {
		Auction auction = auctions.find(auctionId.toLowerCase());
		
		if (auction == null)
			throw new InexistentAuctionException();
		Work work = works.find(workId.toLowerCase());
		if (work == null)
			throw new InexistentWorkInAuctionException();
		if (!((AuctionSetMethods) auction).hasWork(work))
			throw new InexistentWorkInAuctionException();
		if (!((AuctionSetMethods) auction).doesWorkHaveBids(work))
			throw new WorkWithoutBidsException();
		return auction.listWorkBids(work);
	}

	@Override
	public Iterator<Entry<Work, Work>> listWorksValue() throws InexistentSoldWorksException {
		if (this.listWorksByValue.size() == 0)
			throw new InexistentSoldWorksException();
		return this.listWorksByValue.iterator();
	}
}
