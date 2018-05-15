package model.algorithms;

import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.Stack;
import model.data_structures.Vertex;
import model.data_structures.Edge;

public class DepthFirstOrder<K extends Comparable <K>,V,A> {
	
	private boolean[] marked;
	private Stack<Integer> reversePostOrder; // Stack que guardara los 'num' de cada vertice para poderlos ideentificar
	
	public DepthFirstOrder(IDiGraph<K,V,A> graph) {
		reversePostOrder = new Stack<Integer>();
		marked = new boolean[graph.V()];
		for(Vertex<K,V,A> v:graph.getListVertices()) {
			if(!marked[v.getNum()]) {
				dfs(graph,v);
			}
		}
	}

	private void dfs(IDiGraph<K, V, A> graph, Vertex<K, V, A> v) {
		// TODO Auto-generated method stub
		marked[v.getNum()] = true;
		for(Edge<A> e:v.getEdges()) {
			Vertex w = e.getFinalVertex();
			if(!marked[w.getNum()]) {
				dfs(graph,w);
			}
		}
		reversePostOrder.push(v.getNum());
	}
	
	public Iterable<Integer> reversePostorder(){
		return reversePostOrder; 
	 } 
}
