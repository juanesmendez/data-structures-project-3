package model.algorithms;

import model.data_structures.Edge;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.data_structures.Vertex;
import model.vo.InfoEdge;
import model.vo.InfoVertex;
import model.vo.Path;

//Utiliza DFS, solo que guarda los diferentes caminos posibles
public class FindAllPaths<K extends Comparable<K>,V>{

	private LinkedList<Path> listPaths;

	public FindAllPaths(IDiGraph<K,V,InfoEdge> graph, Vertex<K,V,InfoEdge> s, Vertex<K,V,InfoEdge> d) {
		boolean[] isVisited = new boolean[graph.V()];
		this.listPaths = new List<Path>();

		//Initialize the edges list
		LinkedList<Edge<InfoEdge>> edgesList = new List<>();

		if(s.getEdges().size() > 0) {
			findAllPaths(s, d, isVisited,edgesList); //I send the reference to the firs edges list
		}else {
			System.out.println("El vertice origen no contiene arcos.");
		}
	}

	private void findAllPaths(Vertex<K, V, InfoEdge> u, Vertex<K, V, InfoEdge> d, boolean[] isVisited,
			LinkedList<Edge<InfoEdge>> edgesList) {
		// TODO Auto-generated method stub

		/*
		if(current != null) {
			edgesList.add(current);
		}*/

		isVisited[u.getNum()] = true;

		if(u.getNum() == d.getNum()) {

			for(Edge<InfoEdge> e:edgesList) {
				System.out.println(e.getInitialVertex().getNum()+"->"+e.getFinalVertex().getNum());
			}
			System.out.println();
			//edgesList.delete(current);
			//return;
		}

		for(Edge<InfoEdge> e:u.getEdges()) {
			Vertex<K,V,InfoEdge> w = e.getFinalVertex();
			
			if(e.getWeight().getContPeaje() != 0) {
				if(!isVisited[w.getNum()]) { //Chequear por que aca no va a visitar a todos debido a las componentes. Se va a quedar en un loop infinito
					//store current edge in path:



					edgesList.add(e);


					findAllPaths(w,d,isVisited,edgesList);

					//Remove current node in path:



					edgesList.delete(e);
				}
			}

		}

		//Mark the current node:
		isVisited[u.getNum()] = false;


		//edgesList.delete(current);

	}

	public LinkedList<Path> getPaths() {
		return listPaths;
	}

}
