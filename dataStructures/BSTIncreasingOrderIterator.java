package dataStructures;

public class BSTIncreasingOrderIterator<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Stack<BSTNode<K, V>> stack;

	public BSTIncreasingOrderIterator(BSTNode<K, V> root) {
		stack = new StackInList<>();
		this.pushRight(root);

	}

	@Override
	public boolean hasNext() {

		return !stack.isEmpty();
	}

	private void pushRight(BSTNode<K, V> node) {

		while (node != null) {
			stack.push(node);
			node = node.getRight();
		}

	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		if (!hasNext())
			throw new NoSuchElementException();

		BSTNode<K, V> node = stack.pop();
		@SuppressWarnings("unchecked")
		Entry<K, V> entry = node.getEntry();

		if (node.getLeft() != null)
			pushRight(node.getLeft());

		return entry;
	}

	@Override
	public void rewind() {
		
	}

}