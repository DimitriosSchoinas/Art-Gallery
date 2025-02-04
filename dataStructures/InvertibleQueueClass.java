package dataStructures;

public class InvertibleQueueClass<E> extends QueueInList<E> implements InvertibleQueue<E> {

	public InvertibleQueueClass() {
		
		super();
	}
	
	@Override
	public void invert() {
		 DoubleList<E> tempList = new DoubleList<>();
	        while (!this.isEmpty()) {
	            tempList.addLast(this.dequeue());
	        }
	        while (!tempList.isEmpty()) {
	            this.enqueue(tempList.removeFirst());
	        }
	}

}
