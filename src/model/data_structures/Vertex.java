package model.data_structures;


public class Vertex<K extends Comparable<K>,V, A> implements Comparable<Vertex<K,V,A>>{ //Toco volverla publica , cambiarla a private cuando se pueda
	
	private int num;
	private K id;
	private V value;
	private boolean marked;
	private int inDegree;
	private int outDegree;
	private int component;
	private LinkedList<Edge<A>> edges;
	
	public Vertex(K id, V value) {
		this.id = id;
		this.value = value;
		this.marked = false;
		this.inDegree = 0;
		this.outDegree = 0;
		this.component = 0; //Chequear en que valor deberia inicializar component
		this.edges = new List<>();
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
	
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	public LinkedList<Edge<A>> getEdges() {
		return this.edges;
	}
	public void setEdges(LinkedList<Edge<A>> edges) {
		this.edges = edges;
	}
	
	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}

	public void addEdge(Vertex vertexFini, A infoEdge) {
		// TODO Auto-generated method stub
		this.outDegree++;
		vertexFini.setInDegree(vertexFini.getInDegree()+1);
		this.edges.add(new Edge<>(infoEdge, this, vertexFini)); //I add the edge to the list of edges of the Vertex. Just in ome direction (DirectedGraph)
	}
	
	@Override
	public int compareTo(Vertex<K, V, A> o) {
		// TODO Auto-generated method stub
		if(this.getNum() < o.getNum()) {
			return -1;
		}else if(this.getNum() > o.getNum()) {
			return 1;
		}
		return 0;
	}
}
