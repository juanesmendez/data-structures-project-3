package model.vo;

import java.awt.List;

import model.data_structures.HashTableLP;
import model.data_structures.IHashTableLP;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;

public class Component<K extends Comparable<K>,V,A> implements Comparable<Component<K,V,A>>{
	
	private int color;
	private int numVertices;
	private IHashTableLP<Integer,Vertex<K,V,A>> hashTableVertices;
	
	public Component(int color) {
		this.color = color;
		this.numVertices = 0;
		this.hashTableVertices = new HashTableLP<>();
		
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getNumVertices() {
		return numVertices;
	}
	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}
	
	public IHashTableLP<Integer, Vertex<K, V, A>> getHashTableVertices() {
		return hashTableVertices;
	}
	public void setHashTableVertices(IHashTableLP<Integer, Vertex<K, V, A>> hashTableVertices) {
		this.hashTableVertices = hashTableVertices;
	}
	@Override
	public int compareTo(Component o) {
		// TODO Auto-generated method stub
		if(this.color < o.color) {
			return -1;
		}else if(this.color > o.getColor()) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Color: "+this.color+"\nNumero de vertices: "+this.numVertices;
	}
	
	public void agregarVertice() {
		this.numVertices++;
	}
	
	public void addVertex(Vertex<K,V,A> vertex) {
		this.hashTableVertices.put(vertex.getNum(), vertex);
	}
	
}
