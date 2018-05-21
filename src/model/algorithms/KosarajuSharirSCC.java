package model.algorithms;

import model.data_structures.Edge;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;

public class KosarajuSharirSCC<K extends Comparable<K>,V,A> {
	
	private boolean marked[];
	private int[] id;
	private int count;
	
	public KosarajuSharirSCC(IDiGraph<K,V,A> graph) {
		marked = new boolean[graph.V()];
		id = new int[graph.V()];
		DepthFirstOrder<K,V,A> dfs = new DepthFirstOrder<>(graph.reverse());
		
		for(int v:dfs.reversePostorder()) {
			//Vertex<K,V,A> vertex = graph.getVertexByNum(v);
			if(!marked[v]) {
				dfs(graph,v);
				count++;
			}
		}
	}

	private void dfs(IDiGraph<K, V, A> graph, int v) {
		// TODO Auto-generated method stub
		marked[v] = true;
		id[v] = count;
		//Aqui agrego el numero count como atributo del vertice:
		graph.getVerticesHashTableByNum().get(v).setComponent(count);
		
		for(Edge<A> e: graph.getVertexByNum(v).getEdges()) {
			Vertex<K,V,A> w = e.getFinalVertex();
			if(!marked[w.getNum()]) {
				dfs(graph,w.getNum());
			}
		}
	}
	
	public boolean stronglyConnected(Vertex<K,V,A> v, Vertex<K,V,A> w) {
		return id[v.getNum()] == id[w.getNum()];
	}

	public int[] getId() {
		return id;
	}

	
	
}
