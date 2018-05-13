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
				IDiGraph<String,InfoVertex,InfoEdge> grafo= Controller.cargarGrafo();

				System.out.println("NUMERO DE VERTICES EN EL GRAFO: "+grafo.V());
				System.out.println("NUMERO DE ARCOS EN EL GRAFO: "+grafo.E());
				System.out.println();


				break;
			case 2:
				fin=true;
				sc.close();


				break;
			case 3:
				
				break;

			}
		}
	}

	private static void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 3----------------------");
		System.out.println("1. Cargar grafo apartir de archivo JSON");
		System.out.println("2. Salir");
		System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}


}
