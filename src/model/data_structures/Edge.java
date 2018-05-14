package model.data_structures;


public class Edge<A> implements Comparable <Edge<A>>{ //Cambiar a private cuando se pueda

	A weight;
	boolean marked;
	Vertex initialVertex;
	Vertex finalVertex;

	public Edge(A weight, Vertex initialVertex, Vertex finalVertex) {
		this.weight = weight;
		this.marked = false;
		this.initialVertex = initialVertex;
		this.finalVertex = finalVertex;

	}

	public Vertex getInitialVertex() {
		return initialVertex;
	}

	public Vertex getFinalVertex() {
		return finalVertex;
	}

	public A getWeight() {
		return weight;
	}

	public void setWeight(A weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge<A> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
