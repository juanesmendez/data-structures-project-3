package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.data_structures.Queue.Node;

public class Queue <T> implements IQueue<T> {

	
	private Node first;
	private Node last;
	private int size;
	
	public Queue() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public class Node<T> {
		T item;
		Node next;
		
		public Node() {
			this.item = null;
			this.next = null;
		}
		public Node(T item) {
			this.item = item;
			this.next = null;
		}

		public T getItem() {
			return item;
		}

		public void setItem(T item) {
			this.item = item;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	
	
	@Override
	public void enqueue(T item) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(item);
		if(isEmpty()) {
			this.first = node;
			this.last = node;
			this.size++;
		}else {
			this.last.setNext(node);
			this.last = node;
			this.size++;
		}
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		T item = (T) this.first.getItem();
		this.first = this.first.getNext();
		this.size--;
		if(isEmpty()) {
			this.last = null;
		}
		return item;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(this.size == 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T>{
		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(current == null) {
				throw new NoSuchElementException("There is no current"); //It knows the exception because Iterator interface implements excepions
			}
			
			T item = (T) current.getItem();
			current = current.getNext();
			return item;
		}
		
	}



	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

}
