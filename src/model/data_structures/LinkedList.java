package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Abstract Data Type for a linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add: add a new element T 
 * delete: delete the given element T 
 * get: get the given element T (null if it doesn't exist in the list)
 * size: return the the number of elements
 * get: get an element T by position (the first position has the value 0) 
 * listing: set the listing of elements at the first element
 * getCurrent: return the current element T in the listing (return null if it doesn't exists)
 * next: advance to next element in the listing (return if it exists)
 * @param <T>
 */
public interface LinkedList <T extends Comparable<T>> extends Iterable<T> {
	
	
	public void add(T item);
	public void delete(T item);
	public T get(T item);
	public boolean add(T item, Comparator<T> comparator);
	public void addInOrder(T item);
	public int size();
	public T get(int pos);
	public void listing();
	public T getCurrent();
	public T next();
	public Iterator<T> iterator();
}
