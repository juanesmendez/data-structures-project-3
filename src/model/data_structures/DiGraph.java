package model.data_structures;

public class DiGraph<K extends Comparable<K>, V, A> implements IDiGraph<K,V,A>{
	
	
	private int numVertices;
	private int numEdges;
	private IHashTableLP<K,Vertex<K,V,A>> vertices;
	
	private LinkedList<Vertex<K,V,A>> listVertices; //Para trabajar internamente la marcada de los vertices
	private LinkedList<K> listVerticesKeys;
	
	public DiGraph() {
		this.vertices = new HashTableLP<K,Vertex<K,V,A>>();
		this.listVertices = new List<Vertex<K,V,A>>();
		this.listVerticesKeys = new List<K>();
		this.numVertices=0;
		this.numEdges = 0;
		
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
		Vertex<K,V,A> vertex = this.vertices.get(idVertex);
		if(vertex == null) { //I check if the vertex doesnt exist
			vertex = new Vertex<K, V, A>(idVertex, infoVertex);
			vertex.setNum(this.numVertices); //Le agrego un numero que permitira hacer mas facil los algoritmos
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
		Vertex<K,V,A> vertexIni = vertices.get(idVertexIni);
		Vertex<K,V,A> vertexFini = vertices.get(idVertexFin);
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
		Vertex<K,V,A> vertex = vertices.get(idVertex);
		
		if(vertex == null) {
			return null;
		}
		
		
		return (V) vertex.getValue();
	}
	
	public Vertex<K,V,A> getVertexByNum(int num) {
		
		for(Vertex v:this.listVertices) {
			if(v.getNum() == num) {
				return v;
			}
		}
		
		return null;
		
	}

	@Override
	public void setInfoVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub
		if(idVertex == null) {
			throw new IllegalArgumentException("Argument to get is null");
		}
		
		Vertex<K,V,A> vertex = vertices.get(idVertex);
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
		Vertex<K,V,A> vertexIni = vertices.get(idVertexIni);
		Vertex<K,V,A> vertexFin = vertices.get(idVertexFin);
		
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
		Vertex<K,V,A> vertexIni = vertices.get(idVertexIni);
		Vertex<K,V,A> vertexFin = vertices.get(idVertexFin);
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
		Vertex<K,V,A> vertex;
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
	public Iterable<Vertex<K, V, A>> getListVertices() {
		// TODO Auto-generated method stub
		Iterable<Vertex<K, V, A>> returnList = this.listVertices;
		return returnList;
	}

	
	
}
