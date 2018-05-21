package model.algorithms;

import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;

public class DepthFirstSearch<K extends Comparable<K>,V,A> {
	private boolean marked[];
	
	public DepthFirstSearch(IDiGraph<K,V,A> graph, Vertex<K,V,A> s) {
		marked = new boolean[graph.V()];
		dfs(graph,s);
	}

	private void dfs(IDiGraph<K,V,A> graph, Vertex<K,V,A> v) {
		// TODO Auto-generated method stub
		marked[v.getNum()] =  true;
		
		for(Edge<A> e:v.getEdges()) {
			Vertex<K,V,A> w = e.getFinalVertex(); //Vertex final
			if(!marked[w.getNum()]) {
				dfs(graph, w);
			}	
		}
	}
	
	public boolean visited(Vertex<K,V,A> v) {
		return marked[v.getNum()];
	}
	
	
}
