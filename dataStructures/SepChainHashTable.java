package dataStructures;

import java.io.Serializable;

/**
 * Separate Chaining Hash table implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 */

public class SepChainHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty separate chaining hash table, with the specified
	 * initial capacity. Each position of the array is initialized to a new ordered
	 * list maxSize is initialized to the capacity.
	 * 
	 * @param capacity defines the table capacity.
	 */
	@SuppressWarnings("unchecked")
	public SepChainHashTable(int capacity) {
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = (Dictionary<K, V>[]) new Dictionary[arraySize];
		for (int i = 0; i < arraySize; i++) {
			// TODO: Original comentado para nao dar erro de compilacao.
			table[i] = new OrderedDoubleList<K, V>();
		}
		maxSize = capacity;
		currentSize = 0;
	}

	public SepChainHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		if (this.isFull()) {
			// TODO: left as an exercise.
			// Original commented, to compile.
			this.rehash();
		}

		int hashValue = hash(key);

		if (table[hashValue] == null)
			table[hashValue] = new OrderedDoubleList<K, V>();

		V oldValue = table[hashValue].insert(key, value);
		if (oldValue == null)
			currentSize++;

		return oldValue;

		// TODO: Left as an exercise.

	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		// TODO Auto-generated method stub

		int newSize = HashTable.nextPrime(table.length * 2);
		Dictionary<K, V>[] newTable = (Dictionary<K, V>[]) new Dictionary[newSize];
		for (int i = 0; i < newSize; i++) {
			newTable[i] = new OrderedDoubleList<K, V>();
		}

		List<Entry<K, V>> tempEntries = new DoubleList<>();
		for (Dictionary<K, V> dict : table) {
			if (dict != null) {
				Iterator<Entry<K, V>> dictIterator = (DoubleListIterator<Entry<K, V>>) dict.iterator();
				while (dictIterator.hasNext()) {
					tempEntries.addLast(dictIterator.next());
				}
			}
		}

		currentSize = 0;
		table = newTable;

		Iterator<Entry<K, V>> it = tempEntries.iterator();
		while (it.hasNext()) {
			Entry<K, V> entry = it.next();
			insert(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(K key) {
		// TODO: Left as an exercise.

		int hashValue = hash(key);

		if (table[hashValue] == null)
			return null;

		V valueToRemove = table[hashValue].remove(key);
		if (valueToRemove != null)
			currentSize--;

		return valueToRemove;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		// TODO: Left as an exercise.
		List<Entry<K, V>> entries = new DoubleList<Entry<K, V>>();

		for (Dictionary<K, V> dict : table) {
			if (dict != null) {
				Iterator<Entry<K, V>> it = (DoubleListIterator<Entry<K, V>>) dict.iterator();
				while (it.hasNext()) {
					Entry<K, V> entry = it.next();
					entries.addLast(entry);
				}
			}
		}

		return (Iterator<Entry<K, V>>) entries.iterator();
	}
}
