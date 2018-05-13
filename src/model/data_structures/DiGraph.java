package model.data_structures;

public class DiGraph<K extends Comparable<K>, V, A> implements IDiGraph<K,V,A>{
	
	
	private int numVertices;
	private int numEdges;
	private IHashTableLP<K,Vertex<K,V>> vertices;
	
	
	private LinkedList<Vertex<K,V>> listVertices; //Para trabajar internamente la marcada de los vertices
	
	private LinkedList<K> listVerticesKeys;
	
	public DiGraph() {
		this.vertices = new HashTableLP<K,Vertex<K,V>>();
		this.listVertices = new List<Vertex<K,V>>();
		this.listVerticesKeys = new List<K>();
		this.numVertices=0;
		this.numEdges = 0;
		
	}
	
	public class Vertex<K extends Comparable<K>,V> implements Comparable<Vertex<K,V>>{ //Toco volverla publica , cambiarla a private cuando se pueda
		
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
			return edges;
		}
		public void setEdges(LinkedList<Edge<A>> edges) {
			this.edges = edges;
		}
		public void addEdge(Vertex vertexFini, A infoEdge) {
			// TODO Auto-generated method stub
			this.edges.add(new Edge<A>(infoEdge, this, vertexFini)); //I add the edge to the list of edges of the Vertex. Just in ome direction (DirectedGraph)
		}
		@Override
		public int compareTo(Vertex<K,V> o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		
		
	}
	
	public class Edge<A> implements Comparable <Edge<A>>{ //Cambiar a private cuando se pueda
		
		A weight;
		boolean marked;
		Vertex<K,V> initialVertex;
		Vertex<K,V> finalVertex;
		
		public Edge(A weight, Vertex initialVertex, Vertex finalVertex) {
			this.weight = weight;
			this.marked = false;
			this.initialVertex = initialVertex;
			this.finalVertex = finalVertex;
			
		}

		public Vertex<K,V> getInitialVertex() {
			return initialVertex;
		}

		public Vertex<K,V> getFinalVertex() {
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
	
	@Override
	public int V() {
		// TODO Auto-generated method stub
		return this.numVertices;
	}

	@Override
	public int E() {
		// TODO Auto-generated method stub
		return this.numEdges;
	}

	@Override
	public void addVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub
		Vertex<K,V> vertex = this.vertices.get(idVertex);
		if(vertex == null) { //I check if the vertex doesnt exist
			vertex = new Vertex<K, V>(idVertex, infoVertex);
			this.vertices.put(idVertex, vertex);
			this.numVertices++;
			this.listVertices.add(vertex); //I add the vertex to the list of vertices
			this.listVerticesKeys.add(idVertex);
		}
	}

	@Override
	public void addEdge(K idVertexIni, K idVertexFin, A infoEdge) {
		// TODO Auto-generated method stub
		if(idVertexIni == null || idVertexFin == null || infoEdge  == null) {
			throw new IllegalArgumentException("Argument to get is null");
		}
		Vertex<K,V> vertexIni = vertices.get(idVertexIni);
		Vertex<K,V> vertexFini = vertices.get(idVertexFin);
		if(vertexIni == null || vertexFini == null) {
			throw new NullPointerException("Vertex not found");
		}
		vertexIni.addEdge(vertexFini, infoEdge);
		numEdges ++;
	}

	@Override
	public V getInfoVertex(K idVertex) {
		// TODO Auto-generated method stub
		if(idVertex == null) {
			throw new IllegalArgumentException("Argument to get is null");
		}
		Vertex<K,V> vertex = vertices.get(idVertex);
		
		if(vertex == null) {
			return null;
		}
		
		
		return (V) vertex.getValue();
	}

	@Override
	public void setInfoVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub
		if(idVertex == null) {
			throw new IllegalArgumentException("Argument to get is null");
		}
		
		Vertex<K,V> vertex = vertices.get(idVertex);
		if(vertex == null) {
			throw new NullPointerException("Vertex not found"); //Check if I should use this exception
		}
		if(vertex!=null) {
			vertex.setValue(infoVertex);
			
		}
	}

	@Override
	public A getInfoEdge(K idVertexIni, K idVertexFin) {
		// TODO Auto-generated method stub
		if((idVertexIni == null) || (idVertexFin == null)) {
			throw new IllegalArgumentException();
		}
		A infoEdge;
		Vertex<K,V> vertexIni = vertices.get(idVertexIni);
		Vertex<K,V> vertexFin = vertices.get(idVertexFin);
		
		if((vertexIni == null) || (vertexFin == null)) {
			throw new NullPointerException("Vertex not found");
		}
		LinkedList<Edge<A>> edges = vertexIni.getEdges();
		if(edges != null) { //If the edges list is different to null (I prevent it from throwing an error)
			for(Edge<A> e:edges) {
				if(e.initialVertex.equals(vertexIni) && e.finalVertex.equals(vertexFin)) { //Check becuase .equals() can cause errors
					infoEdge = (A) e.getWeight();
					return infoEdge;
				}
			}
		}
		
		return null;
	}

	@Override
	public void setInfoEdge(K idVertexIni, K idVertexFin, A infoEdge) {
		// TODO Auto-generated method stub
		Vertex<K,V> vertexIni = vertices.get(idVertexIni);
		Vertex<K,V> vertexFin = vertices.get(idVertexFin);
		LinkedList<Edge<A>> edges = vertexIni.getEdges();
		
		if((idVertexIni == null) || (idVertexFin == null)) {
			throw new IllegalArgumentException();
		}
		if((vertexIni == null) || (vertexFin == null)) {
			throw new NullPointerException("Vertex not found");
		}
		if(edges != null) { //If the edges list is different to null (I prevent it from throwing an error)
			for(Edge<A> e:edges) {
				if(e.finalVertex.equals(vertexFin)) { //Check becuase .equals() can cause errors
					e.setWeight(infoEdge);
					break;
				}
			}
		}
	}

	@Override
	public Iterable<K> adj(K idVertex) {
		// TODO Auto-generated method stub
		Vertex<K,V> vertex;
		LinkedList<K> adj = new List<K>();
		LinkedList<Edge<A>> edges; 
		if(idVertex == null) {
			throw new IllegalArgumentException();
		}
		vertex  = vertices.get(idVertex);
		if(vertex == null) {
			throw new NullPointerException("Vertex not found");
		}
		edges = vertex.getEdges();
		if(edges != null) {
			for(Edge<A> e:edges) {
				K id = (K) e.getFinalVertex().getId();
				adj.add(id);
			}
		}
		
		return adj;
	}

	@Override
	public LinkedList<K> getListVertexKeys() {
		// TODO Auto-generated method stub
		return this.listVerticesKeys;
	}

	@Override
	public Iterable<DiGraph<K,V,A>.Vertex<K, V>> getListVertices() {
		// TODO Auto-generated method stub
		Iterable<DiGraph<K,V,A>.Vertex<K, V>> returnList = this.listVertices;
		return returnList;
	}

	
	
}
