package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import model.data_structures.List.Node;

public class List<T extends Comparable<T>> implements LinkedList<T> ,Iterable<T>{
	private Node head;
	private Node last;
	private int size;
	private Node current;
	//Add reference to last one
	public List() {
		this.head = null;
		this.size = 0;
		//Iterator initialization missing
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
	public void add(T item) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(item);
		if(this.head == null) {
			this.head = node;
			this.last = node;
			this.size++;
		}else {
			Node<T> aux = this.last;
			aux.setNext(node);
			this.last = node;
			this.size++;
		}
	}

	@Override
	public void delete(T item) {
		// TODO Auto-generated method stub
		if(this.head!=null) {
			Node<T> aux = this.head;
			if(aux.getItem() == item) {
				this.head = aux.getNext();
				aux = null;
				if(this.head.getNext() == null) {
					this.last = this.head;
				}
				this.size--;
			}else {
				while(aux.getNext()!=null) {
					if(aux.getNext().getItem() == item) {
						Node<T> temp = aux.getNext();
						Node<T> siguiente = temp.getNext();
						temp = null;
						aux.setNext(siguiente);
						if(siguiente == null) {
							this.last = aux;
						}
						this.size--;
						break;
					}
					aux = aux.getNext();
				}
			}
		}
	}

	@Override
	public T get(T item) {
		// TODO Auto-generated method stub
		
		//In this method I need to use method compareTo from the T object. If it finds it, return the object with all of the information.
		Node<T> aux = this.head;
		while(aux != null) {
			if(aux.getItem().compareTo(item) == 0) {
				return aux.getItem();
			}
			aux = aux.getNext();
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public T get(int pos) {
		// TODO Auto-generated method stub
		T data = null;
		int cont = 0;
		
		Node<T> aux = this.head;
		
		while(aux !=null) {
			if(cont == pos) {
				data = aux.getItem();
				return data;
			}
			aux = aux.getNext();
			cont++;
		}
		return data;
	}

	@Override
	public void listing() {
		// TODO Auto-generated method stub
		this.current = this.head;
	}

	@Override
	public T getCurrent() {
		// TODO Auto-generated method stub
		return (T) this.current.getItem();
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		this.current = this.current.next;
		return (T) this.current.getItem();
	}

	
	public void addBeginning(T item) {
		Node node = new Node<T>(item);
		if(this.head == null) {
			this.head = node;
			this.size++;
		}else {
			Node<T> anteriorPrimero = this.head;
			this.head = node;
			node.setNext(anteriorPrimero);
			this.size++;
		}
	}
	

	@Override
	public boolean add(T item, Comparator<T> comparator) {
		// TODO Auto-generated method stub
		boolean respuesta = false; 
		Node<T> node = new Node<T>(item);
		if (this.head == null){ 
			this.head = node;
			respuesta = true;
		}else if ( comparator.compare(item, (T) this.head.getItem())<0){
			node.setNext(this.head);
			this.head = node;
			respuesta = true;
		}else if(comparator.compare(item, (T)this.head.getItem())>0) {
			Node<T> anterior = this.head;
			Node<T> actual = this.head.getNext();
			while(actual!=null && comparator.compare(item, (T) actual.getItem())>0) {
				anterior = actual;
				actual = actual.getNext();
			}
			//Chequear si sí es <=0
			if(actual == null || comparator.compare(item, (T)actual.getItem()) <= 0) {
				anterior.setNext(node);
				node.setNext(actual);
				respuesta = true;
			}
			
		}
		
		if(respuesta == true) {
			this.size++;
		}
		return respuesta;
		
	}
	
	@Override
	public void addInOrder(T item) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(item);
		Node<T> aux;
		Node<T> temp;
		Node<T> anterior = null;
		if(this.head == null) {
			this.head = node;
			this.last = node;
			this.size++;
		}else {
			aux = this.head;
			while(aux != null) {
				if(aux == this.head) {
					if(node.getItem().compareTo(aux.getItem())<0 || node.getItem().compareTo(aux.getItem()) == 0) {
						if(this.size == 1) {
							temp = this.head;
							this.head = node;
							node.setNext(temp);
							this.last = temp;
							this.size++;
							break;
						}else {
							temp = this.head;
							this.head = node;
							node.setNext(temp);
							this.size++;
							break;
						}
					}else {
						if(this.size == 1) {
							aux.setNext(node);
							this.last = node;
							this.size++;
							break;
						}
					}
				}else {
					if(node.getItem().compareTo(aux.getItem()) < 0 || node.getItem().compareTo(aux.getItem()) == 0){
						anterior.setNext(node);
						node.setNext(aux);
						this.size++;
						break;
					}else {
						if(aux == this.last) {
							temp = this.last;
							temp.setNext(node);
							this.last = node;
							this.size++;
							break;
						}
					}
				}
				anterior = aux;
				aux = aux.getNext();
			}
		}
	
	}

	

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T>{
		private Node current = head;

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
