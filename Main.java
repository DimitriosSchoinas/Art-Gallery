import java.util.Scanner;
import java.io.*;
import Exceptions.*;

import Galery.*;
import dataStructures.*;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Main class handles user
 *         input and output
 */
public class Main {

	/**
	 * Program feedback
	 */
	private static final String EXIT_MESSAGE = "Obrigado. Ate a proxima.\n";
	private static final String ADD_USER_MESSAGE = "Registo de utilizador executado.\n\n";
	private static final String ADD_ARTIST_MESSAGE = "Registo de artista executado.\n\n";
	private static final String REMOVE_USER_MESSAGE = "Remocao de utilizador executada.\n\n";
	private static final String ADD_WORK_MESSAGE = "Registo de obra executado.\n\n";
	private static final String INFO_USER_MESSAGE = "%s %s %d %s\n\n";
	private static final String INFO_ARTIST_MESSAGE = "%s %s %s %d %s\n\n";
	private static final String INFO_WORK_MESSAGE = "%s %s %d %d %s %s\n\n";
	private static final String CREATE_AUCTION_MESSAGE = "Registo de leilao executado.\n\n";
	private static final String ADD_WORK_AUCTION_MESSAGE = "Obra adicionada ao leilao.\n\n";
	private static final String BID_MESSAGE = "Proposta aceite.\n\n";
	private static final String CLOSE_AUCTION_MESSAGE_DEFAULT = "Leilao encerrado.\n";
	private static final String CLOSE_AUCTION_LIST_SOLD = "%s %s %s %s %d\n";
	private static final String CLOSE_AUCTION_LIST_NOT_SOLD = "%s %s sem propostas de venda.\n";
	private static final String LIST_AUCTION_WORKS_MESSAGE = "%s %s %d %d %s %s\n";
	private static final String LIST_ARTIST_WORKS_MESSAGE = "%s %s %d %d\n";
	private static final String LIST_BIDS_WORK_MESSAGE = "%s %s %d\n";
	private static final String LIST_WORKS_VALUE_MESSAGE = "%s %s %d %d %s %s\n";
	
	/**
	 * Error messages
	 */
	
	private static final String ARTIST_HAS_WORK_IN_AUCTION = "Artista com obras em leilao.\n\n";
	private static final String ARTIST_WITHOUT_WORKS = "Artista sem obras.\n\n";
	private static final String AUCTION_WITHOUT_WORKS = "Leilao sem obras.\n\n";
	private static final String ALREADY_EXISTING_AUCTION = "Leilao existente.\n\n";
	private static final String ALREADY_EXISTING_USER = "Utilizador existente.\n\n";
	private static final String ALREADY_EXISTING_WORK = "Obra existente.\n\n";
	private static final String INEXISTENT_ARTIST = "Artista inexistente.\n\n";
	private static final String INEXISTENT_AUCTION = "Leilao inexistente.\n\n";
	private static final String INEXISTENT_SOLD_WORKS = "Nao existem obras ja vendidas em leilao.\n\n";
	private static final String INEXISTENT_USER = "Utilizador inexistente.\n\n";
	private static final String INEXISTENT_WORK = "Obra inexistente.\n\n";
	private static final String INEXISTENT_WORK_IN_AUCTION = "Obra inexistente no leilao.\n\n";
	private final static String PRICE_UNDER_MIN_PRICE = "Valor proposto abaixo do valor minimo.\n\n";
	private static final String UNDERAGE = "Idade inferior a 18 anos.\n\n";
	private static final String USER_HAS_ACTIVE_BIDS = "Utilizador com propostas submetidas.\n\n";
	private static final String WORK_WITHOUT_BIDS = "Obra sem propostas.\n\n";
	

	private static final String GALERY_FILE = "testes.txt";

	/**
	 * 
	 * User Commands
	 *
	 */
	private enum Command {
		QUIT("quit"), ADD_USER("adduser"), ADD_ARTIST("addartist"), REMOVE_USER("removeuser"), ADD_WORK("addwork"),
		INFO_USER("infouser"), INFO_ARTIST("infoartist"), INFO_WORK("infowork"), CREATE_AUCTION("createauction"),
		ADD_WORK_AUCTION("addworkauction"), BID("bid"), CLOSE_AUCTION("closeauction"),
		LIST_AUCTION_WORKS("listauctionworks"), LIST_ARTIST_WORKS("listartistworks"), LIST_BIDS_WORK("listbidswork"),
		LIST_WORKS_VALUE("listworksbyvalue"), UNKNOWN("");

		private final String commandInputName;

		private Command(String commandInputName) {
			this.commandInputName = commandInputName;
		}

		public String getCommandInputName() {
			return this.commandInputName;
		}
	}

	/**
	 * Main program. Invokes the command interpreter
	 * 
	 * @param args - arguments for running the application. Not used in this
	 *             program.
	 */
	public static void main(String[] args) {

		Main.executeCommands();

	}

	/**
	 * command interpreter
	 */
	private static void executeCommands() {
		Scanner in = new Scanner(System.in);
		Command command = readCommand(in);
		Galery galery = load();

		while (!command.equals(Command.QUIT)) {
			switch (command) {
			case ADD_USER -> {
				addUser(galery, in);
			}
			case ADD_ARTIST -> {
				addArtist(galery, in);
			}
			case REMOVE_USER -> {
				removeUser(galery, in);
			}
			case ADD_WORK -> {
				addWork(galery, in);
			}
			case INFO_USER -> {
				infoUser(galery, in);
			}
			case INFO_ARTIST -> {
				infoArtist(galery, in);
			}
			case INFO_WORK -> {
				infoWork(galery, in);
			}
			case CREATE_AUCTION -> {
				createAuction(galery, in);
			}
			case ADD_WORK_AUCTION -> {
				addWorkAuction(galery, in);
			}
			case BID -> {
				bid(galery, in);
			}
			case CLOSE_AUCTION -> {
				closeAuction(galery, in);
			}
			case LIST_AUCTION_WORKS -> {
				listAuctionWorks(galery, in);
			}
			case LIST_ARTIST_WORKS -> {
				listArtistWorks(galery, in);
			}
			case LIST_BIDS_WORK -> {
				listBidsWork(galery, in);
			}
			case LIST_WORKS_VALUE -> {
				listWorksValue(galery, in);
			}

			default -> {
				System.out.println();
			}
			}
			command = readCommand(in);
		}
		System.out.printf("\n");
		System.out.printf(Main.EXIT_MESSAGE);
		in.close();
		save(galery);
	}

	/**
	 * saves the output in the text file
	 * 
	 * @param galery current gallery
	 */
	private static void save(Galery galery) {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GALERY_FILE));
			oos.writeObject(galery);
			oos.flush();
			oos.close();
		} catch (IOException e) {
		}

	}

	/**
	 * loads the Gallery object
	 * 
	 * @return return the Gallery object
	 */
	private static Galery load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GALERY_FILE));
			Galery galery = (Galery) ois.readObject();
			ois.close();
			return galery;
		} catch (IOException e) {
			return new GaleryClass();
		} catch (ClassNotFoundException e) {
			return new GaleryClass();
		}

	}

	/**
	 * adds an user in the gallery
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void addUser(Galery galery, Scanner in) {

		String login, name, email;
		int age;

		login = in.next();
		name = in.nextLine().trim();
		age = in.nextInt();
		email = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			galery.addUser(login, name, age, email);
			System.out.printf(ADD_USER_MESSAGE);
		} catch (UnderageException e) {
			System.out.printf(UNDERAGE);
		} catch (ExistentUserException e) {
			System.out.printf(ALREADY_EXISTING_USER);
		}
	}

	/**
	 * adds an artist in the gallery
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void addArtist(Galery galery, Scanner in) {

		String login, name, email, artisticName;
		int age;

		login = in.next();
		name = in.nextLine().trim();
		artisticName = in.nextLine().trim();
		age = in.nextInt();
		email = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			galery.addArtist(login, name, artisticName, age, email);
			System.out.printf(ADD_ARTIST_MESSAGE);
		} catch (UnderageException e) {
			System.out.printf(UNDERAGE);
		} catch (ExistentUserException e) {
			System.out.printf(ALREADY_EXISTING_USER);
		}
	}

	/**
	 * removes an user in the gallery
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void removeUser(Galery galery, Scanner in) {
		String login;

		login = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			galery.removeUser(login);
			System.out.printf(REMOVE_USER_MESSAGE);
		} catch (InexistentUserException e) {
			System.out.printf(INEXISTENT_USER);
		} catch (UserHasActiveBidsException e) {
			System.out.printf(USER_HAS_ACTIVE_BIDS);
		} catch (ArtistHasWorksInAuctionException e) {
			System.out.printf(ARTIST_HAS_WORK_IN_AUCTION);
		}

	}

	/**
	 * adds a work in the gallery
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void addWork(Galery galery, Scanner in) {
		String idWork, loginArtist, name;
		int year;

		idWork = in.next();
		loginArtist = in.next();
		year = in.nextInt();
		name = in.nextLine().trim();
		System.out.printf("\n");

		try {
			galery.addWork(idWork, loginArtist, year, name);
			System.out.printf(ADD_WORK_MESSAGE);
		} catch (ExistentWorkException e) {
			System.out.printf(ALREADY_EXISTING_WORK);
		} catch (InexistentUserException e) {
			System.out.printf(INEXISTENT_USER);
		} catch (InexistentArtistException e) {
			System.out.printf(INEXISTENT_ARTIST);
		}

	}

	/**
	 * gives info about the user
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void infoUser(Galery galery, Scanner in) {

		String login, userName, email;
		int age;

		login = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			User user = galery.getUser(login);
			userName = user.getUserName();
			age = user.getAge();
			email = user.getEmail();
			System.out.printf(INFO_USER_MESSAGE, login, userName, age, email);
		} catch (InexistentUserException e) {
			System.out.printf(INEXISTENT_USER);
		}

	}

	/**
	 * gives info about the artist
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void infoArtist(Galery galery, Scanner in) {

		String login, name, email, artisticName;
		int age;

		login = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			Artist artist = galery.getArtist(login);
			name = artist.getUserName();
			artisticName = artist.getArtisticName();
			age = artist.getAge();
			email = artist.getEmail();
			System.out.printf(INFO_ARTIST_MESSAGE, login, name, artisticName, age, email);
		} catch (InexistentUserException e) {
			System.out.printf(INEXISTENT_USER);
		} catch (InexistentArtistException e) {
			System.out.printf(INEXISTENT_ARTIST);
		}
	}

	/**
	 * gives info about the work
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void infoWork(Galery galery, Scanner in) {

		String idWork, workName, loginArtist, artistName;
		int year, maxSellingPrice;

		idWork = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			Work work = galery.getWork(idWork);
			workName = work.getWorkName();
			year = work.getCreationYear();
			maxSellingPrice = work.getHighestSellingPrice();
			loginArtist = work.getLoginArtist();
			artistName = work.getArtistName();
			System.out.printf(INFO_WORK_MESSAGE, idWork, workName, year, maxSellingPrice, loginArtist, artistName);
		} catch (InexistentWorkException e) {
			System.out.printf(INEXISTENT_WORK);
		}
	}

	/**
	 * creates an auction in the gallery
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void createAuction(Galery galery, Scanner in) {

		String auctionId;

		auctionId = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			galery.createAuction(auctionId);
			System.out.printf(CREATE_AUCTION_MESSAGE);
		} catch (ExistentAuctionException e) {
			System.out.printf(ALREADY_EXISTING_AUCTION);
		}

	}

	/**
	 * adds a work to an auction
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void addWorkAuction(Galery galery, Scanner in) {

		String auctionId, workId;
		int minPrice;

		auctionId = in.next();
		workId = in.next();
		minPrice = in.nextInt();
		in.nextLine();
		System.out.printf("\n");

		try {
			galery.addWorkAuction(auctionId, workId, minPrice);
			System.out.printf(ADD_WORK_AUCTION_MESSAGE);
		} catch (InexistentAuctionException e) {
			System.out.printf(INEXISTENT_AUCTION);
		} catch (InexistentWorkException e) {
			System.out.printf(INEXISTENT_WORK);
		}
	}

	/**
	 * the artist bids in the work of a specified auction
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void bid(Galery galery, Scanner in) {

		String auctionId, workId, login;
		int value;

		auctionId = in.next();
		workId = in.next();
		login = in.next();
		value = in.nextInt();
		System.out.printf("\n");

		try {
			galery.bid(auctionId, workId, login, value);
			System.out.printf(BID_MESSAGE);
		} catch (InexistentUserException e) {
			System.out.printf(INEXISTENT_USER);
		} catch (InexistentAuctionException e) {
			System.out.printf(INEXISTENT_AUCTION);
		} catch (InexistentWorkInAuctionException e) {
			System.out.printf(INEXISTENT_WORK_IN_AUCTION);
		} catch (PriceUnderMinPriceException e) {
			System.out.printf(PRICE_UNDER_MIN_PRICE);
		}
	}

	/**
	 * closes the specified auction
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void closeAuction(Galery galery, Scanner in) {

		String auctionId, workId, workName, buyerLogin, buyerName;
		int purchaseValue;

		auctionId = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			Iterator<Work> it = galery.listClosedAuction(auctionId);
			galery.closeAuction(auctionId);
			System.out.printf(CLOSE_AUCTION_MESSAGE_DEFAULT);
			if (it != null) {
				while (it.hasNext()) {
					Work work = it.next();
					workId = work.getWorkId();
					workName = work.getWorkName();
					if (work.getLastWinningBid() != null) {
						buyerLogin = work.getLastWinningBid().getUserBid().getLogin();
						buyerName = work.getLastWinningBid().getUserBid().getUserName();
						purchaseValue = work.getLastWinningBid().getAmountOfBid();
						System.out.printf(CLOSE_AUCTION_LIST_SOLD, workId, workName, buyerLogin, buyerName,
								purchaseValue);
					} else {
						System.out.printf(CLOSE_AUCTION_LIST_NOT_SOLD, workId, workName);
					}
				}
			}
			System.out.println();
		} catch (InexistentAuctionException e) {
			System.out.printf(INEXISTENT_AUCTION);
		}
	}

	/**
	 * lists the works of an auction
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void listAuctionWorks(Galery galery, Scanner in) {

		String auctionId, workId, workName, artistLogin, artistName;
		int year, highestSellingPrice;

		auctionId = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			Iterator<Work> it = galery.listAuctionWorks(auctionId);
			while (it.hasNext()) {
				Work work = it.next();
				workId = work.getWorkId();
				workName = work.getWorkName();
				artistLogin = work.getLoginArtist();
				artistName = work.getArtistName();
				year = work.getCreationYear();
				highestSellingPrice = work.getHighestSellingPrice();
				System.out.printf(LIST_AUCTION_WORKS_MESSAGE, workId, workName, year, highestSellingPrice, artistLogin,
						artistName);
			}
			System.out.println();
		} catch (InexistentAuctionException e) {
			System.out.printf(INEXISTENT_AUCTION);
		} catch (AuctionWithoutWorksException e) {
			System.out.printf(AUCTION_WITHOUT_WORKS);
		}
	}

	/**
	 * lists the works of an artist
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void listArtistWorks(Galery galery, Scanner in) {

		String login, workId, workName;
		int year, highestSellingPrice;

		login = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			Iterator<Entry<String, Work>> it = galery.listArtistWorks(login);
			while (it.hasNext()) {
				Work work = it.next().getValue();
				workId = work.getWorkId();
				workName = work.getWorkName();
				year = work.getCreationYear();
				highestSellingPrice = work.getHighestSellingPrice();
				System.out.printf(LIST_ARTIST_WORKS_MESSAGE, workId, workName, year, highestSellingPrice);
			}
			System.out.println();
		} catch (InexistentUserException e) {
			System.out.printf(INEXISTENT_USER);
		} catch (InexistentArtistException e) {
			System.out.printf(INEXISTENT_ARTIST);
		} catch (ArtistWithoutWorksException e) {
			System.out.printf(ARTIST_WITHOUT_WORKS);
		}
	}

	/**
	 * lists the bids of a work in an auction
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void listBidsWork(Galery galery, Scanner in) {

		String auctionId, workId, login, name;
		int bidValue;

		auctionId = in.next();
		workId = in.next();
		in.nextLine();
		System.out.printf("\n");

		try {
			Iterator<Bid> it = galery.listBidsWork(auctionId, workId);
			while (it.hasNext()) {
				Bid bid = it.next();
				login = bid.getUserBid().getLogin();
				name = bid.getUserBid().getUserName();
				bidValue = bid.getAmountOfBid();
				System.out.printf(LIST_BIDS_WORK_MESSAGE, login, name, bidValue);

			}
			System.out.println();
		} catch (InexistentAuctionException e) {
			System.out.printf(INEXISTENT_AUCTION);
		} catch (InexistentWorkInAuctionException e) {
			System.out.printf(INEXISTENT_WORK_IN_AUCTION);
		} catch (WorkWithoutBidsException e) {
			System.out.printf(WORK_WITHOUT_BIDS);
		}
	}

	/**
	 * lists the sold works by their max sell value
	 * 
	 * @param galery current gallery
	 * @param in     scanner input
	 */
	private static void listWorksValue(Galery galery, Scanner in) {

		String workId, workName, artistLogin, artistName;
		int year, highestSellingPrice;

		in.nextLine();
		System.out.printf("\n");

		try {
			Iterator<Entry<Work, Work>> it = galery.listWorksValue();
			while (it.hasNext()) {
				Work work = it.next().getValue();
				workId = work.getWorkId();
				workName = work.getWorkName();
				artistLogin = work.getLoginArtist();
				artistName = work.getArtistName();
				year = work.getCreationYear();
				highestSellingPrice = work.getHighestSellingPrice();
				System.out.printf(LIST_WORKS_VALUE_MESSAGE, workId, workName, year, highestSellingPrice, artistLogin,
						artistName);

			}
			System.out.println();
		} catch (InexistentSoldWorksException e) {
			System.out.printf(INEXISTENT_SOLD_WORKS);
		}
	}

	private static Command readCommand(Scanner in) {
		String input = in.next().toLowerCase();
		for (Command c : Command.values())
			if (c.getCommandInputName().equals(input))
				return c;
		return Command.UNKNOWN;
	}

}
