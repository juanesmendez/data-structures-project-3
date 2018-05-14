package model.data_structures;


public class Vertex<K extends Comparable<K>,V, A> implements Comparable<Vertex<K,V,A>>{ //Toco volverla publica , cambiarla a private cuando se pueda
	
	K id;
	V value;
	boolean marked;
	LinkedList<Edge<A>> edges;
	
	public Vertex(K id, V value) {
		this.id = id;
		this.value = value;
		this.marked = false;
		this.edges = new List<>();
	}
	public K getId() {
		return id;
	}
	public void setId(K id) {
		this.id = id;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public boolean isMarked() {
		return marked;
	}
	public void setMarked(boolean mark) {
		this.marked = mark;
	}
	
	public LinkedList<Edge<A>> getEdges() {
		return this.edges;
	}
	public void setEdges(LinkedList<Edge<A>> edges) {
		this.edges = edges;
	}
	public void addEdge(Vertex vertexFini, A infoEdge) {
		// TODO Auto-generated method stub
		this.edges.add(new Edge<>(infoEdge, this, vertexFini)); //I add the edge to the list of edges of the Vertex. Just in ome direction (DirectedGraph)
	}
	
	@Override
	public int compareTo(Vertex<K, V, A> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
