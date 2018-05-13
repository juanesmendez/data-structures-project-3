package controller;

import api.ITaxiTripsManager;
import model.data_structures.IDiGraph;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.vo.*;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager  manager = new TaxiTripsManager();
	
	public static IDiGraph<String,InfoVertex,InfoEdge> cargarGrafo() {
		return manager.cargarGrafo();
	}
	
}
