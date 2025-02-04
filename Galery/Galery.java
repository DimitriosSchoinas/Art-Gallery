package Galery;

import Exceptions.*;
import java.io.*;

import dataStructures.Entry;
import dataStructures.Iterator;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles the information
 *         about a Gallery
 */
public interface Galery extends Serializable {

	/**
	 * adds an user
	 * 
	 * @param login user login
	 * @param name  user name
	 * @param age   user age
	 * @param email user email
	 * @throws UnderageException
	 * @throws ExistentUserException
	 */
	void addUser(String login, String name, int age, String email) throws UnderageException, ExistentUserException;

	/**
	 * adds an artist
	 * 
	 * @param login        artist login
	 * @param name         artist name
	 * @param artisticName artist artistic name
	 * @param age          artist age
	 * @param email        artist email
	 * @throws UnderageException
	 * @throws ExistentUserException
	 */
	void addArtist(String login, String name, String artisticName, int age, String email)
			throws UnderageException, ExistentUserException;

	/**
	 * removes an user
	 * 
	 * @param login user login
	 * @throws InexistentUserException
	 * @throws UserHasActiveBidsException
	 * @throws ArtistHasWorksInAuctionException
	 */
	void removeUser(String login)
			throws InexistentUserException, UserHasActiveBidsException, ArtistHasWorksInAuctionException;

	/**
	 * adds a work
	 * 
	 * @param workId      work id
	 * @param artistLogin artist login
	 * @param year        work publish year
	 * @param name        name of the work
	 * @throws ExistentWorkException
	 * @throws InexistentUserException
	 * @throws InexistentArtistException
	 */
	void addWork(String workId, String artistLogin, int year, String name)
			throws ExistentWorkException, InexistentUserException, InexistentArtistException;

	/**
	 * gets the user
	 * 
	 * @param login user login
	 * @return the user
	 * @throws InexistentUserException
	 */
	User getUser(String login) throws InexistentUserException;

	/**
	 * gets the artist
	 * 
	 * @param login artist login
	 * @return the artist
	 * @throws InexistentUserException
	 * @throws InexistentArtistException
	 */
	Artist getArtist(String login) throws InexistentUserException, InexistentArtistException;

	/**
	 * gets the work
	 * 
	 * @param idWork work id
	 * @return the work
	 * @throws InexistentWorkException
	 */
	Work getWork(String idWork) throws InexistentWorkException;

	/**
	 * creates an auction
	 * 
	 * @param auctionId auction id
	 * @throws ExistentAuctionException
	 */
	void createAuction(String auctionId) throws ExistentAuctionException;

	/**
	 * adds a work to an auction
	 * 
	 * @param auctionId auction id
	 * @param workId    work id
	 * @param minPrice  minimum sell price
	 * @throws InexistentAuctionException
	 * @throws InexistentWorkException
	 */
	void addWorkAuction(String auctionId, String workId, int minPrice)
			throws InexistentAuctionException, InexistentWorkException;

	/**
	 * the user makes a bid in a work during an auction
	 * 
	 * @param auctionId auction id
	 * @param workId    work id
	 * @param login     user login
	 * @param value     value of the bid
	 * @throws InexistentAuctionException
	 * @throws InexistentWorkException
	 * @throws InexistentUserException
	 * @throws PriceUnderMinPriceException
	 */
	void bid(String auctionId, String workId, String login, int value) throws InexistentAuctionException,
			InexistentUserException, PriceUnderMinPriceException, InexistentWorkInAuctionException;

	/**
	 * lists works value when closing the auction
	 * 
	 * @param auctionId
	 * @return the iterator that lists works value when closing the auction
	 * @throws InexistentAuctionException
	 */
	Iterator<Work> listClosedAuction(String auctionId) throws InexistentAuctionException;

	/**
	 * lists the works of the auction
	 * 
	 * @param auctionId
	 * @return the iterator that lists the works of the auction
	 * @throws InexistentAuctionException
	 * @throws AuctionWithoutWorksException
	 */
	Iterator<Work> listAuctionWorks(String auctionId) throws InexistentAuctionException, AuctionWithoutWorksException;

	/**
	 * closes the auction
	 * 
	 * @param auctionId auction id
	 * @throws InexistentAuctionException
	 */
	void closeAuction(String auctionId) throws InexistentAuctionException;

	/**
	 * lists the works of the artist
	 * 
	 * @param login
	 * @return the iterator that lists the works of the artist
	 * @throws InexistentUserException
	 * @throws InexistentArtistException
	 * @throws ArtistWithoutWorksException
	 */
	Iterator<Entry<String, Work>> listArtistWorks(String login)
			throws InexistentUserException, InexistentArtistException, ArtistWithoutWorksException;

	/**
	 * lists the bids of the work
	 * 
	 * @param auctionId auction id
	 * @param workId    work id
	 * @return iterator that lists the bids of the work
	 * @throws InexistentAuctionException
	 * @throws InexistentWorkInAuctionException
	 * @throws WorkWithoutBidsException
	 */
	Iterator<Bid> listBidsWork(String auctionId, String workId)
			throws InexistentAuctionException, InexistentWorkInAuctionException, WorkWithoutBidsException;

	/**
	 * lists the works by value
	 * 
	 * @return iterator that lists the works by value
	 * @throws InexistentSoldWorksException
	 */
	Iterator<Entry<Work, Work>> listWorksValue() throws InexistentSoldWorksException;
}
