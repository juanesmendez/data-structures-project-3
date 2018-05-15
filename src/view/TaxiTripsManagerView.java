package view;

import java.util.Scanner;

import controller.Controller;
import model.algorithms.DepthFirstSearch;
import model.data_structures.DiGraph;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;
import model.logic.TaxiTripsManager;
import model.vo.*;

public class TaxiTripsManagerView 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		String taxiId;
		
		
		IDiGraph<String,InfoVertex,InfoEdge> grafo = null; //BORRAR ESTA DECLARACION
		while(!fin)
		{
			printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				grafo= Controller.cargarGrafo();
				System.out.println();
				System.out.println("NUMERO DE VERTICES EN EL GRAFO: "+grafo.V());
				System.out.println("NUMERO DE ARCOS EN EL GRAFO: "+grafo.E());
				System.out.println();
				//Solo de prueba
				for(Vertex v:grafo.getListVertices()) {
					System.out.println("In-degree: "+v.getInDegree());
					System.out.println("Out-degree: "+v.getOutDegree());
					System.out.println();
				}

				break;
			case 1:
				Vertex<String,InfoVertex,InfoEdge> vertex;
				try {
					vertex = Controller.verticeMasCongestionado();
					System.out.println("INFORMACION VERTICE MAS CONGESTIONADO EN CHICAGO");
					System.out.println("Latitud: "+vertex.getValue().getLatitudReferencia());
					System.out.println("Longitud: "+vertex.getValue().getLongitudReferencia());
					System.out.println("Total servicios que salen: "+vertex.getOutDegree());
					System.out.println("Total servicios que entran: "+vertex.getInDegree());
					System.out.println();
					

				}catch(Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				
				break;
			case 2:
				
				/*
				Vertex<String,InfoVertex,InfoEdge> s = grafo.getVertexByNum(300);
				DepthFirstSearch<String,InfoVertex,InfoEdge> dfs = new DepthFirstSearch<String,InfoVertex,InfoEdge>(grafo, s);
				
				for(Vertex<String,InfoVertex,InfoEdge> v:grafo.getListVertices()) {
					boolean marked = dfs.visited(v);
					
					System.out.println("Vertice "+v.getNum()+": "+marked);
					
					
				}
				*/
				
				break;
			
				
			case 3:
				fin=true;
				sc.close();
				break;
			}
		}
	}

	private static void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 3----------------------");
		System.out.println("0. Cargar grafo apartir de archivo JSON");
		System.out.println("1. Mostrar informacion del vertice mas congestionado en Chicago.");
		System.out.println("2. Prueba");
		System.out.println("3. Salir");
		System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}


}
