package Galery;

import java.io.Serializable;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877 Handles the information
 *         about the Artist
 */
public interface Artist extends User, Serializable {

	/**
	 * gets the artistic name
	 * 
	 * @return the artistic name
	 */
	String getArtisticName();
}
