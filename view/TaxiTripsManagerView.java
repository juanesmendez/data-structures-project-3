package view;

import java.util.Scanner;

import controller.Controller;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.vo.*;

public class TaxiTripsManagerView 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		String taxiId;
		while(!fin)
		{
			printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:

				//imprime menu cargar
				printMenuCargar();

				//opcion cargar
				int optionCargar = sc.nextInt();

				//directorio json
				String linkJson = "";
				switch (optionCargar)
				{
				//direccion json pequeno
				case 1:

					linkJson = TaxiTripsManager.DIRECCION_SMALL_JSON;
					break;

					//direccion json mediano
				case 2:

					linkJson = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
					break;

					//direccion json grande
				case 3:

					linkJson = TaxiTripsManager.DIRECCION_LARGE_JSON;
					break;
				}

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();
				
				System.out.println("Digite una distancia dx con la que se medira el radio de los discos que guardaran la informacion de cada vertice del grafo:");
				double distance = sc.nextDouble();
				
				//Cargar data
				IDiGraph<String,InfoVertex,InfoEdge> graph = Controller.cargarSistema(linkJson,distance);



				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");
				System.out.println();
				System.out.println("NUMERO DE VERTICES EN EL GRAFO: "+graph.V());
				System.out.println("NUMERO DE ARCOS EN EL GRAFO: "+graph.E());
				System.out.println("Distancia dx: "+distance);
				System.out.println();
				
				break;
			case 2:
				
				IDiGraph<String,InfoVertex,InfoEdge> grafo= Controller.cargarGrafoJSON();
				
				System.out.println("NUMERO DE VERTICES EN EL GRAFO: "+grafo.V());
				System.out.println("NUMERO DE ARCOS EN EL GRAFO: "+grafo.E());
				System.out.println();
				
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
		System.out.println("1. Cargar toda la informacion dada una fuente de datos (small,medium, large) y crear un grafo apartir de los datos");
		System.out.println("2. Cargar grafo apartir de archivo JSON");
		System.out.println("3. Salir");
		System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea cargar?");
		System.out.println("-- 1. Small");
		System.out.println("-- 2. Medium");
		System.out.println("-- 3. Large");
		System.out.println("-- Type the option number for the task, then press enter: (e.g., 1)");
	}
}
