package model.data_structures;

public interface IMaxPQ<Key> extends Iterable<Key>{
	
	public boolean isEmpty();
	public int size();
	public Key max();
	public void insert(Key x);
	public Key delMax();
	
	
	
}
