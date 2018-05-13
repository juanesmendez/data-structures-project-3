package model.data_structures;

public interface IMinPQ<Key> extends Iterable<Key> {
	
	public boolean isEmpty();
	public int size();
	public Key min();
	public void insert(Key x);
	public Key delMin();
	
}
