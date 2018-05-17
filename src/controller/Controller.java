package controller;

import api.ITaxiTripsManager;
import model.data_structures.Edge;
import model.data_structures.IDiGraph;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;
import model.logic.TaxiTripsManager;
import model.vo.*;

public class Controller 
{
	/**
	 * modela el manejador de la clase logica
	 */
	private static ITaxiTripsManager  manager = new TaxiTripsManager();
	
	public static IDiGraph<String,InfoVertex,InfoEdge> cargarGrafo() {
		return manager.cargarGrafo();
	}
	
	public static Vertex<String,InfoVertex,InfoEdge> verticeMasCongestionado() throws Exception{
		return manager.verticeMasCongestionado();
	}
	
	public static LinkedList<Component<String,InfoVertex,InfoEdge>> calcularComponentesFuertementeConexos() throws Exception{
		return manager.calcularComponentesFuertementeConexos();
	}
	
	public static Iterable<Edge<InfoEdge>> encontrarCaminoMenorDistancia(){
		return manager.encontrarCaminoMenorDistancia();
	}
	
}
