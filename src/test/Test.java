package test;

import model.algorithms.FindAllPathsGeneric;
import model.algorithms.KosarajuSharirSCC;
import model.data_structures.DiGraph;
import model.data_structures.IDiGraph;
import model.data_structures.Vertex;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IDiGraph<Integer, Integer, Double> graph = new DiGraph<>();
		
		graph.addVertex(0, 0);
		graph.addVertex(1, 1);
		graph.addVertex(2, 2);
		graph.addVertex(3, 3);
		graph.addVertex(4, 4);
		graph.addVertex(5, 5);
		
		graph.addVertex(6, 6);
		
		graph.addEdge(0, 1, 1.0);
		graph.addEdge(1, 3, 2.0);
		graph.addEdge(0, 2, 4.0);
		graph.addEdge(2, 3, 5.0);
		graph.addEdge(0, 4, 6.0);
		graph.addEdge(4, 2, 7.0);
		graph.addEdge(4, 3, 8.0);
		//----
		graph.addEdge(1, 5, 9.0);
		graph.addEdge(5, 1, 2.0);
		graph.addEdge(1, 0, 2.0);
		graph.addEdge(2, 1, 1.0);
		
		Vertex<Integer,Integer,Double> vi = graph.getVertexByNum(0);
		Vertex<Integer,Integer,Double> vf = graph.getVertexByNum(3);
		
		KosarajuSharirSCC<Integer, Integer, Double> ko = new KosarajuSharirSCC<>(graph);
		int[] idArray = ko.getId();
		
		for(int i=0;i<idArray.length;i++) {
			System.out.println(i+": "+idArray[i]);
		}
		System.out.println();
		FindAllPathsGeneric<Integer, Integer, Double> fal = new FindAllPathsGeneric<Integer,Integer,Double>(graph, vi, vf, null);
	}

}
