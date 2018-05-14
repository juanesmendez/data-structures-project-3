package model.data_structures;


public interface IDiGraph<K extends Comparable<K>,V,A> {
	
	
	/**
	 * Returns the number of vertices in the graph.
	 * @return The number of vertices in the DiGraph
	 */
	public int V();
	/**
	 * Returns the number of edges in the graph.
	 * @return The number of edges in the DiGraph
	 */
	public int E();
	/**
	 * Adds a vertex to the graph.
	 * @param idVertex Key of the vertex to add
	 * @param infoVertex Value of the vertex to add
	 */
	public void addVertex(K idVertex, V infoVertex);
	/**
	 * Adds an edge to the graph.
	 * @param idVertexIni Key of the initial vertex
	 * @param idVertexFin Key of the final vertex
	 * @param infoEdge Info of the edge to add
	 */
	public void addEdge(K idVertexIni, K idVertexFin, A infoEdge);
	/**
	 * Returns the information o a vertex.
	 * @param idVertex key of the vertex to find
	 * @return Value of the vertex to find
	 */
	public V getInfoVertex(K idVertex);
	/**
	 * Changes the information of a vertex.
	 * @param idVertex Key of the vertex to set info
	 * @param infoVertex Value of the new info of the vertex
	 */
	public void setInfoVertex(K idVertex, V infoVertex);
	/**
	 * Returns the information of an edge.
	 * @param idVertexIni Key of the initial vertex to find
	 * @param idVertexFin Key of the final vertex to find
	 * @return Information of the edge
	 */
	public A getInfoEdge(K idVertexIni, K idVertexFin);
	/**
	 * Changes the information of an edge.
	 * @param idVertexIni Key of the initial vertex to find
	 * @param idVertexFin Key of the final vertex to find
	 * @param infoEdge New information of the edge
	 */
	public void setInfoEdge(K idVertexIni, K idVertexFin, A infoEdge);
	/**
	 * Returns the keys of the vertices adjacent to the given vertex.
	 * @param idVertex Key of the vertex to find adjacents
	 * @return Iterable interface with the Keys of all the vertices adjacent to the given Key vertex
	 */
	public Iterable<K> adj(K idVertex);
	/**
	 * Return the keys of the vertices in the graph
	 * @return List of keys of the vertices in graph
	 */
	public LinkedList<K> getListVertexKeys();
	
	public Iterable<Vertex<K,V,A>> getListVertices();
}
