package dataStructures;

public class ConcatenableQueueClass<E> extends QueueInList<E> implements ConcatenableQueue<E> {

	public ConcatenableQueueClass(){
		super();
	}
	
	
	@Override
	public void append(ConcatenableQueue<E> queue) {
		
		if ( queue instanceof ConcatenableQueueClass<?>) {
			
			DoubleList<E> l1= (DoubleList<E>)this.list;
			@SuppressWarnings("unchecked")
			DoubleList<E> l2= (DoubleList<E>) queue;
					l1.append(l2);
		}else {
			while(!queue.isEmpty()) {
				this.enqueue(queue.dequeue());
			}
		}
	}

}
