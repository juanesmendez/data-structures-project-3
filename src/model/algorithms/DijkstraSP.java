package model.algorithms;

import model.data_structures.Edge;
import model.data_structures.IDiGraph;
import model.data_structures.IIndexMinPQ;
import model.data_structures.IMinPQ;
import model.data_structures.IndexMinPQ;
import model.data_structures.MinPQ;
import model.data_structures.Stack;
import model.data_structures.Vertex;
import model.vo.InfoEdge;

//Solo funcionara con el tipo InfoEdge

public class DijkstraSP<K extends Comparable<K>,V,A> {

	private Edge<InfoEdge>[] edgeTo;
	private double[] distTo; //El valor puede ser un Float o Integer o Double
	private IIndexMinPQ<Double> pq;

	public DijkstraSP(IDiGraph<K,V,InfoEdge> graph, Vertex<K,V,A> s, String criterio){
		edgeTo = new Edge[graph.V()];
		distTo = new double[graph.V()];
		pq = new IndexMinPQ<Double>(graph.V());

		for(int v=0;v<graph.V();v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}

		distTo[s.getNum()] = 0.0;

		pq.insert(s.getNum(), 0.0);

		while(!pq.isEmpty()) {
			int v = pq.delMin();
			for(Edge<InfoEdge> e: graph.getVertexByNum(v).getEdges()) {
				relax(e,criterio);
			}
		}
	}

	private void relax(Edge<InfoEdge> e, String criterio) {
		// TODO Auto-generated method stub
		Vertex from = e.getInitialVertex();
		Vertex to = e.getFinalVertex();

		int v = from.getNum();
		int w = to.getNum();

		if(criterio.equals("distancia")) {
			if(distTo[w] > distTo[v] + e.getWeight().getDistancia()) {
				distTo[w] = distTo[v] + e.getWeight().getDistancia();
				edgeTo[w] = e;
				if(pq.contains(w)) {
					pq.decreaseKey(w, distTo[w]);
				}else {
					pq.insert(w, distTo[w]);
				}
			}
		}else if (criterio.equals("duracion")) {
			if(distTo[w] > distTo[v] + e.getWeight().getSegundos()) {
				distTo[w] = distTo[v] + e.getWeight().getSegundos();
				edgeTo[w] = e;
				if(pq.contains(w)) {
					pq.decreaseKey(w, distTo[w]);
				}else {
					pq.insert(w, distTo[w]);
				}
			}
		}

	}

	public double distTo(Vertex v) {

		return distTo[v.getNum()];
	}

	public Iterable<Edge<InfoEdge>> pathTo(Vertex vertex)
	{
		Stack<Edge<InfoEdge>> path = new Stack<Edge<InfoEdge>>();
		for (Edge<InfoEdge> e = edgeTo[vertex.getNum()]; e != null; e = edgeTo[e.getInitialVertex().getNum()]) {
			path.push(e);
		}
			
		return path;
	}

}
