package dataStructures;

/**
 * Doubly linked list Implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K, V> Generics - K extends Comparable
 * 
 */
class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Node at the head of the list.
	 */
	protected DoubleListNode<Entry<K, V>> head;

	/**
	 * Node at the tail of the list.
	 */
	protected DoubleListNode<Entry<K, V>> tail;

	/**
	 * Number of elements in the list.
	 */
	protected int currentSize;

	/**
	 * Constructor of an empty ordered double linked list. head and tail are
	 * initialized as null. currentSize is initialized as 0.
	 */
	public OrderedDoubleList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	/**
	 * Inserts the Entry element before node after. Precondition: after is not the
	 * head of the ordered double list.
	 * 
	 * @param element - Entry to be inserted
	 * @param after   - Node to be next to the new node
	 */
	protected void addBeforeNode(Entry<K, V> element, DoubleListNode<Entry<K, V>> after) {
		// TODO: Left as an exercise.
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(element, after.getPrevious(), after);
		after.getPrevious().setNext(newNode);
		after.setPrevious(newNode);
		currentSize++;
	}

	/**
	 * Inserts the Entry element at the first position in the list.
	 * 
	 * @param element - Entry to be inserted
	 */
	protected void addFirst(Entry<K, V> element) {
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(element, null, head);
		if (this.isEmpty())
			tail = newNode;
		else
			head.setPrevious(newNode);
		head = newNode;
		currentSize++;
	}

	/**
	 * Inserts the Entry element at the last position in the list.
	 * 
	 * @param element - Entry to be inserted
	 */
	protected void addLast(Entry<K, V> element) {
		// TODO: Left as an exercise.
		DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(element, tail, null);
		if (this.isEmpty())
			head = newNode;
		else
			tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		// TODO: Left as an exercise.

		if (isEmpty())
			throw new EmptyDictionaryException();

		DoubleListNode<Entry<K, V>> currentNode = head;
		Entry<K, V> maxValue = head.getElement();

		while (currentNode != null) {
			if (currentNode.getElement().getKey().compareTo(maxValue.getKey()) > 0)
				maxValue = currentNode.getElement();

			currentNode = currentNode.getNext();
		}

		return maxValue;
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		// TODO: Left as an exercise.
		if (isEmpty())
			throw new EmptyDictionaryException();

		DoubleListNode<Entry<K, V>> currentNode = head;
		Entry<K, V> minValue = head.getElement();

		while (currentNode != null) {
			if (currentNode.getElement().getKey().compareTo(minValue.getKey()) < 0)
				minValue = currentNode.getElement();

			currentNode = currentNode.getNext();
		}

		return minValue;
	}

	/**
	 * Returns the node with the Entry with Key key in the list, if the list
	 * contains this entry. Otherwise returns the next element with the highest key
	 * 
	 * @param key - Key of type K to be searched
	 * @return DoubleListNode<E> where the Entry with key was found, or the one with
	 *         the key immediately after
	 */
	protected DoubleListNode<Entry<K, V>> findNode(K key) {
		// TODO: Left as an exercise.
		DoubleListNode<Entry<K, V>> currentNode = head;

		while (currentNode != null && currentNode.getElement().getKey().compareTo(key) != 0) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	@Override
	public V find(K key) {
		DoubleListNode<Entry<K, V>> node = findNode(key);
		// TODO: Left as an exercise.

		if (node != null && node.getElement().getKey().compareTo(key) == 0)
			return node.getElement().getValue();
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V insert(K key, V value) {
		DoubleListNode<Entry<K, V>> node = findNode(key);
		if ((node != null) && (node.getElement().getKey().compareTo(key) == 0)) {
			// TODO: Left as an exercise.
			V oldValue = node.getElement().getValue();
			Entry<K, V> newNode = new EntryClass<K, V>(key, value);
			node.setElement(newNode);
			return oldValue;
		} else {
			Entry<K, V> newNode = new EntryClass<K, V>(key, value);
			// recommended: re-use addFirst, addLast and addBeforeNode
			// TODO: Left as an exercise.
			addLast(newNode);

			return null;
		}
	}

	@Override
	public boolean isEmpty() {

		return currentSize == 0;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<Entry<K, V>>(head, tail);
	}

	/**
	 * Removes the first node in the list. Pre-condition: the list is not empty.
	 */
	protected void removeFirstNode() {
		head = head.getNext();
		if (head == null)
			tail = null;
		else
			head.setPrevious(null);
		currentSize--;
	}

	/**
	 * Removes and returns the value at the first entry in the list.
	 */
	protected V removeFirst() throws EmptyDictionaryException {
		// TODO: Left as an exercise.
		if (isEmpty())
			throw new EmptyDictionaryException();

		V value = head.getElement().getValue();
		removeFirstNode();
		return value;
	}

	/**
	 * Removes the last node in the list. Pre-condition: the list is not empty.
	 */
	protected void removeLastNode() {
		// TODO: Left as an exercise.
		tail = tail.getPrevious();
		if (tail == null)
			head = null;
		else
			tail.setNext(null);
		currentSize--;
	}

	/**
	 * Removes and returns the value at the last entry in the list.
	 */
	protected V removeLast() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();

		V value = tail.getElement().getValue();
		this.removeLastNode();
		return value;
	}

	/**
	 * Removes the specified node from the list. Pre-condition: the node is neither
	 * the head nor the tail of the list.
	 * 
	 * @param node - middle node to be removed
	 */
	protected void removeMiddleNode(DoubleListNode<Entry<K, V>> node) {
		// TODO: Left as an exercise.
		DoubleListNode<Entry<K, V>> nextNode = node.getNext();
		DoubleListNode<Entry<K, V>> previousNode = node.getPrevious();

		nextNode.setPrevious(previousNode);
		previousNode.setNext(nextNode);
		currentSize--;
	}

	@Override
	public V remove(K key) {
		DoubleListNode<Entry<K, V>> node = findNode(key);
		if ((node == null) || (node.getElement().getKey().compareTo(key) != 0))
			return null;
		else {
			// TODO: Left as an exercise.
			V value = node.getElement().getValue();

			if (node == head)
				removeFirstNode();
			else if (node == tail)
				removeLastNode();
			else
				removeMiddleNode(node);

			return value;
		}
	}

	@Override
	public int size() {
		return currentSize;
	}

}
