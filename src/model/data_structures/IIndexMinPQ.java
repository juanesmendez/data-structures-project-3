package model.data_structures;

import java.util.Iterator;

public interface IIndexMinPQ<Key extends Comparable<Key>> extends Iterable<Integer> {
	
	public boolean isEmpty();
	public int size();
	public void insert(int i, Key key);
	public int minIndex();
	public Key minKey();
	public int delMin();
	public boolean contains(int i);
	public void decreaseKey(int i, Key key);
	public Key keyOf(int i);
	public Iterator<Integer> iterator();
	
}
