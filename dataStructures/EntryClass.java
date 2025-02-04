package dataStructures;

public class EntryClass<K,V> implements Entry{

	private K key;
	private V value;
	
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public K getKey() {
		
		return this.key;
	}

	@Override
	public V getValue() {
		
		return this.value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setKey(Object newKey) {
		this.key = (K) newKey;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object newValue) {
		this.value = (V) newValue;
		
	}

}
