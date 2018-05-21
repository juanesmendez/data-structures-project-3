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
public class FindAllPathsGeneric<K extends Comparable<K>,V,A>{
	
	private LinkedList<Path> listPaths;
	
	public FindAllPathsGeneric(IDiGraph<K,V,A> graph, Vertex<K,V,A> s, Vertex<K,V,A> d,int[] idArray) {
		boolean[] isVisited = new boolean[graph.V()];
		this.listPaths = new List<Path>();
		
		//Initialize the edges list
		LinkedList<Edge<A>> edgesList = new List<>();
		
		findAllPathsGeneric(s, d, isVisited,edgesList,idArray); //I send the reference to the firs edges list
		
	}

	private void findAllPathsGeneric(Vertex<K, V, A> u, Vertex<K, V, A> d, boolean[] isVisited,
			LinkedList<Edge<A>> edgesList,int[] idArray) {
		// TODO Auto-generated method stub
		
		/*
		if(current != null) {
			edgesList.add(current);
		}*/
		
		isVisited[u.getNum()] = true;
		
		if(u.getNum() == d.getNum()) {
			
			for(Edge<A> e:edgesList) {
				System.out.println(e.getInitialVertex().getNum()+"->"+e.getFinalVertex().getNum());
			}
			System.out.println();
			//edgesList.delete(current);
			//return;
		}
		
		for(Edge<A> e:u.getEdges()) {
			Vertex<K,V,A> w = e.getFinalVertex();
			
			if(!isVisited[w.getNum()]) { //Chequear por que aca no va a visitar a todos debido a las componentes. Se va a quedar en un loop infinito
				//store current edge in path:
				
				
				
				edgesList.add(e);
				
				
				findAllPathsGeneric(w,d,isVisited,edgesList,idArray);
				
				//Remove current node in path:
				
				
				
				edgesList.delete(e);
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

