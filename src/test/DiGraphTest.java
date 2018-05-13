package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.DiGraph;
import model.data_structures.IDiGraph;

public class DiGraphTest {

	@Test
	public void testV() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		int numVertices = graph.V();
		assertEquals(1, numVertices);
		graph.addVertex(2, "B");
		numVertices = graph.V();
		assertEquals(2, numVertices);
		graph.addVertex(3, "C");
		numVertices = graph.V();
		assertEquals(3, numVertices);
		graph.addVertex(1, "A");
		numVertices = graph.V();
		assertEquals(3,numVertices);
	}

	@Test
	public void testE() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		Float f = (float) 4.5;
		
		graph.addEdge(1, 2, f);
		int numEdges = graph.E();
		assertEquals(1, numEdges);
		f = (float) 6.9;
		graph.addEdge(2, 3, f);
		numEdges = graph.E();
		assertEquals(2,numEdges);
		f = (float) 7.8;
		graph.addEdge(1, 3, f);
		numEdges = graph.E();
		assertEquals(3,numEdges);
		
		
	}

	@Test
	public void testAddVertex() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		int numVertices = graph.V();
		assertEquals(1, numVertices);
		graph.addVertex(2, "B");
		numVertices = graph.V();
		assertEquals(2, numVertices);
		
		String value = (String)graph.getInfoVertex(2);
		assertEquals("B", value);
		
	}

	@Test
	public void testAddEdge() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		
		graph.addEdge(1, 2, "HOLA");
		int numEdges = graph.E();
		assertEquals(1, numEdges);
		graph.addEdge(2, 3, "CHAO");
		numEdges = graph.E();
		assertEquals(2,numEdges);
		graph.addEdge(1, 3, "COMO ESTAS");
		numEdges = graph.E();
		assertEquals(3,numEdges);
		
		String info = (String)graph.getInfoEdge(1, 3);
		assertEquals("COMO ESTAS", info);
		
		
	}

	@Test
	public void testGetInfoVertex() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		String info= (String) graph.getInfoVertex(1);
		assertEquals("A",info);
		info= (String) graph.getInfoVertex(2);
		assertEquals("B",info);
		info= (String) graph.getInfoVertex(3);
		assertEquals("C",info);
	}

	@Test
	public void testSetInfoVertex() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		
		graph.setInfoVertex(1, "Z");
		String info = (String) graph.getInfoVertex(1);
		assertEquals("Z", info);
		graph.setInfoVertex(2, "X");
		info = (String) graph.getInfoVertex(2);
		assertEquals("X", info);
	}

	@Test
	public void testGetInfoEdge() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		
		graph.addEdge(1, 2, "HOLA");
		graph.addEdge(2, 3, "CHAO");
		graph.addEdge(1, 3, "COMO ESTAS");
		
		String info = (String) graph.getInfoEdge(1, 3);
		assertEquals("COMO ESTAS", info);
		
		info = (String) graph.getInfoEdge(2, 3);
		assertEquals("CHAO", info);
	}

	@Test
	public void testSetInfoEdge() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		
		graph.addEdge(1, 2, "HOLA");
		graph.addEdge(2, 3, "CHAO");
		graph.addEdge(1, 3, "COMO ESTAS");
		
		String info = (String) graph.getInfoEdge(1, 3);
		assertEquals("COMO ESTAS", info);
		
		info = (String) graph.getInfoEdge(2, 3);
		assertEquals("CHAO", info);
		
		graph.setInfoEdge(1, 3, "TE CAMBIE");
		info = (String)graph.getInfoEdge(1, 3);
		assertEquals("TE CAMBIE", info);
		
		graph.setInfoEdge(1, 2, "EL NUEVO MENSAJE");
		info = (String)graph.getInfoEdge(1, 2);
		assertEquals("EL NUEVO MENSAJE", info);
	}

	@Test
	public void testAdj() {
		IDiGraph graph = new DiGraph<>();
		graph.addVertex(1, "A");
		graph.addVertex(2, "B");
		graph.addVertex(3, "C");
		
		graph.addEdge(1, 2, "HOLA");
		graph.addEdge(2, 3, "CHAO");
		graph.addEdge(1, 3, "COMO ESTAS");
		
		Iterable<Integer> it = graph.adj(1);
		int i=0;
		Integer inte;
		for(Integer integer:it) {
			
			System.out.println(integer);
			if(i==0) {
				inte = (int)2;
				assertEquals(inte,integer);
			}
			if(i==1) {
				inte = (int)3;
				assertEquals(inte,integer);
			}
			i++;
		}
	}

}
