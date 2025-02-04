package dataStructures;

public class BSTDecreasingOrderIterator<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Stack<BSTNode<K, V>> stack;

	public BSTDecreasingOrderIterator(BSTNode<K, V> root) {
		stack = new StackInList<>();
		this.pushLeft(root);

	}

	@Override
	public boolean hasNext() {

		return !stack.isEmpty();
	}

	private void pushLeft(BSTNode<K, V> node) {

		while (node != null) {
			stack.push(node);
			node = node.getLeft();
		}
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		if (!hasNext())
			throw new NoSuchElementException();

		BSTNode<K, V> node = stack.pop();
		@SuppressWarnings("unchecked")
		Entry<K, V> entry = node.getEntry();

		if (node.getRight() != null)
			pushLeft(node.getRight());

		return entry;
	}

	@Override
	public void rewind() {

		while (hasNext())
			stack.pop();
	}

}