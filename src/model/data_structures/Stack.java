package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.data_structures.Stack.Node;

public class Stack <T extends Comparable<T>> implements IStack<T>, Iterable<T>{

	private Node top;
	private int size;
	
	public Stack() {
		this.top = null;
		this.size = 0;
	}
	
	public class Node<T extends Comparable<T>> {
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
	public T pop() {
		// TODO Auto-generated method stub
		if(this.top == null) {
			return null;
		}else {
			T item = (T) this.top.getItem();
			this.top = this.top.getNext();
			this.size--;
			return item;
		}
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
	public void push(T item) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(item);
		if(this.top == null) {
			node.setNext(null);
			this.top = node;
			this.size++;
		}else {
			node.setNext(this.top);
			this.top = node;
			this.size++;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T>{
		private Node current = top;

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

}
