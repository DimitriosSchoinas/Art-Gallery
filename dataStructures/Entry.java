package dataStructures;

import java.io.Serializable;

/**
 * Entry Abstract Data Type 
 * Includes description of general methods to be implemented by an entry.
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value  
 */

public interface Entry<K,V> extends Serializable
{
	/**
	 * Returns the key in the entry.
	 * @return key in the entry
	 */
    K getKey( );

	/**
	 * Returns the value in the entry.
	 * @return value in the entry
	 */
    V getValue( );
   
    /**
     * sets a new key
     * @param newKey new key to change
     */
	void setKey(K newKey);
	 /**
     * sets a new value
     * @param newValue new value to change
     */
	void setValue(V newValue);

}
