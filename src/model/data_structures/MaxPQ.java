package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Elaborado con la ayuda de: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/MaxPQ.java.html

public class MaxPQ<Key> implements Iterable<Key>, IMaxPQ<Key> {

	private Key[] pq; //Stores items in the priority queue
	private int n; //Number of items in priority queue
	private Comparator<Key> comparator;  // optional comparator

	public MaxPQ(int initCapacity) { //Initializes priority queue with the given capacity
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	//Initializes an empty priority queue
	public MaxPQ() {
		this(1);
	}

	public MaxPQ(int initCapacity, Comparator<Key> comparator) { //Initializes a priority queue with the given comparator
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	public MaxPQ(Comparator<Key> comparator) { //Initializes an empty priority queue with the given comparator
		this(1, comparator);
	}

	public MaxPQ(Key[] keys) { //Initializes a priority queue with the given array of keys
		n = keys.length;
		pq = (Key[]) new Object[keys.length + 1]; //+1 because priority queu will start in the position "1" leaving position "0" empty
		for (int i = 0; i < n; i++) {
			pq[i+1] = keys[i];
		}
		for (int k = n/2; k >= 1; k--) {
			sink(k);
		}
		assert isMaxHeap();
	}

	public boolean isEmpty() {
		if(n==0) {
			return true;
		} else {
			return false;
		}

	}

	public int size() {
		return n;
	}

	public Key max() { //return the largest key in the priority queue
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	private void resize(int capacity) { //Function that resizes the array
		assert capacity > n;
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= n; i++) { //starts in 1 
			temp[i] = pq[i];
		}
		pq = temp;
	}

	public void insert(Key x) {

		// double size of array if necessary
		if (n == pq.length - 1) {
			resize(2 * pq.length);
		}

		// add x, and percolate it up to maintain heap invariant
		pq[++n] = x;
		swim(n);
		assert isMaxHeap();
	}

	public Key delMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("Priority queue underflow");
		}
		Key max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;     // to avoid loiterig and help with garbage collection
		if ((n > 0) && (n == (pq.length - 1) / 4)) { //Resizes if its 1/4 of the original elements
			resize(pq.length / 2);
		}
		assert isMaxHeap();
		return max;
	}

	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= n) {
			int j = 2*k;
			if (j < n && less(j, j+1)) {
				j++;
			}
			if (!less(k, j)) {
				break;
			}
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		if (comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	private boolean isMaxHeap(int k) {
		if (k > n) {
			return true;
		}
		int left = 2*k;
		int right = 2*k + 1;
		if (left  <= n && less(k, left)) {
			return false;
		}
		if (right <= n && less(k, right)) {
			return false;
		}
		return isMaxHeap(left) && isMaxHeap(right);
	}


	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Key> {

		// create a new pq
		private MaxPQ<Key> copy;

		// add all items to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator() {
			if (comparator == null) {
				copy = new MaxPQ<Key>(size());
			}else {
				copy = new MaxPQ<Key>(size(), comparator);
			}
			for (int i = 1; i <= n; i++) {
				copy.insert(pq[i]);
			}

		}

		public boolean hasNext()  { 
			return !copy.isEmpty();
		}
		public void remove()      { 
			throw new UnsupportedOperationException(); 
		}

		public Key next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return copy.delMax();
		}
	}

	
}
