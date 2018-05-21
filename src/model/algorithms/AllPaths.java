package model.algorithms;

import java.util.Set;
import java.util.TreeSet;

import model.vo.InfoEdge;
import model.vo.Path;
import model.data_structures.*;

public class AllPaths<K extends Comparable<K>,V> {
	private IStack<Vertex<K,V,InfoEdge>> path  = new Stack<>();   // the current path
	//private SET<Edge<InfoEdge>> onPath  = new SET<>();     // the set of vertices on the path
	private Set<Vertex<K,V,InfoEdge>> onPath = new TreeSet<>();
	private IStack<Edge<InfoEdge>> edgesPath = new Stack<>();
	
	private LinkedList<Path> pathList = new List<>();
	
	public AllPaths(IDiGraph<K,V,InfoEdge> G, Vertex<K,V,InfoEdge> s, Vertex<K,V,InfoEdge> t) {
		enumerate(G, s, t);
		
	}

	// use DFS
	private void enumerate(IDiGraph<K,V,InfoEdge> G, Vertex<K,V,InfoEdge> v, Vertex<K,V,InfoEdge> t) {

		// add node v to current path from s
		path.push(v);
		onPath.add(v);

		// found path from s to t - currently prints in reverse order because of stack
		if (v.getNum() == t.getNum()) {
			
			Path myPath = new Path(edgesPath);
			pathList.add(myPath);
			return;
			//StdOut.println(path);
		}
		// consider all neighbors that would continue path with repeating a node
		else {
			//----------
			for(Edge<InfoEdge> e:v.getEdges()) {
				Vertex<K,V,InfoEdge> w = e.getFinalVertex();
				if(!onPath.contains(w)) {
					edgesPath.push(e);
					enumerate(G,w,t);
				}
			}
			
		}

		// done exploring from v, so remove from path
		path.pop();
		onPath.remove(v);
		edgesPath.pop();
	}
	
	public LinkedList<Path> getPaths(){
		return this.pathList;
	}

}
