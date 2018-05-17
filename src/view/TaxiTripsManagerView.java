package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;

import controller.Controller;
import model.algorithms.DepthFirstSearch;
import model.data_structures.DiGraph;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;
import model.logic.TaxiTripsManager;
import model.vo.*;
import sun.awt.image.URLImageSource;

import com.teamdev.jxbrowser.chromium.Browser;

public class TaxiTripsManagerView 
{

	public static final String GOOGLE_STATIC_MAPS_API = "https://maps.googleapis.com/maps/api/staticmap?";
	public static final String API_KEY = "AIzaSyDv2q8A1yyFsVxxcPmkWBhebJQrnaANL34";




	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		String taxiId;

		String url;
		String center;
		String zoom;
		String size;
		String markers;
		String key;


		IDiGraph<String,InfoVertex,InfoEdge> grafo = null; //BORRAR ESTA DECLARACION
		while(!fin)
		{
			printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				//Dentro de este llamado incluyo la carga del archivo .csv con datos de coordenadas que posteriormente seran usadas.
				grafo= Controller.cargarGrafo();
				System.out.println();
				System.out.println("NUMERO DE VERTICES EN EL GRAFO: "+grafo.V());
				System.out.println("NUMERO DE ARCOS EN EL GRAFO: "+grafo.E());
				System.out.println();
				//Solo de prueba
				/*
				for(Vertex v:grafo.getListVertices()) {
					System.out.println("In-degree: "+v.getInDegree());
					System.out.println("Out-degree: "+v.getOutDegree());
					InfoVertex info = (InfoVertex) v.getValue();
					//System.out.println("Latitud:"+info.getLatitudReferencia());
					System.out.println();
				}*/
				
				break;
			case 1:

				try {
					Vertex<String,InfoVertex,InfoEdge> vertex = Controller.verticeMasCongestionado();
					System.out.println("INFORMACION VERTICE MAS CONGESTIONADO EN CHICAGO");
					System.out.println("Latitud: "+vertex.getValue().getLatitudReferencia());
					System.out.println("Longitud: "+vertex.getValue().getLongitudReferencia());
					System.out.println("Total servicios que salen: "+vertex.getOutDegree());
					System.out.println("Total servicios que entran: "+vertex.getInDegree());
					System.out.println();


					url = GOOGLE_STATIC_MAPS_API;
					center = "center="+vertex.getValue().getLatitudReferencia()+","+vertex.getValue().getLongitudReferencia();
					zoom = "zoom=15";
					size = "size=600x600";
					String scale = "scale=1";
					markers = "markers=size:mid%7C"+vertex.getValue().getLatitudReferencia()+","+vertex.getValue().getLongitudReferencia();
					key = "key="+API_KEY;
					url = url+center+"&"+zoom+"&"+size+"&"+scale+"&"+markers+"&"+key;
					//String url = "https://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&key=AIzaSyDv2q8A1yyFsVxxcPmkWBhebJQrnaANL34";

					launchMap(url);


				}catch(Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}




				break;
			case 2:

				try {
					LinkedList<Component<String,InfoVertex,InfoEdge>> componentes = Controller.calcularComponentesFuertementeConexos();
					System.out.println("COMPONENTES: ");
					System.out.println();
					int mayor = Integer.MIN_VALUE;
					Component<String,InfoVertex,InfoEdge> componenteMasGrande = null;
					for(Component<String,InfoVertex,InfoEdge> c: componentes) {
						if(c.getNumVertices() > mayor) {
							mayor = c.getNumVertices();
							componenteMasGrande = c;
						}
						System.out.println(c.toString());
						System.out.println();
					}

					System.out.println("NUMERO TOTAL DE COMPONENTES "+componentes.size());
					System.out.println();
					System.out.println("COMPONENTE MAS GRANDE: ");
					System.out.println(componenteMasGrande.toString());

					url = GOOGLE_STATIC_MAPS_API;
					center = "center="+41.881832+","+-87.623177;
					zoom = "zoom=10";
					size = "size=400x400";
					String scale2 = "scale=2";
					markers = "markers=size:tiny%7C";

					key = API_KEY;

					for(Integer n:componenteMasGrande.getHashTableVertices().keys()) {
						Vertex<String,InfoVertex,InfoEdge> v = componenteMasGrande.getHashTableVertices().get(n);
						if(v.getValue().getLatitudReferencia() == 0 || v.getValue().getLongitudReferencia() == 0) {
							continue;
						}
						markers = markers+v.getValue().getLatitudReferencia()+","+v.getValue().getLongitudReferencia()+"%7C";
					}
					markers = markers.substring(0, markers.length()-3);
					System.out.println("markers value: "+markers);

					//url = url+center+"&"+zoom+"&"+size+"&"+markers+"&"+key;
					url = url+size+"&"+scale2+"&"+markers+"&"+key;
					launchMap(url);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
		System.out.println("2. Calcular componentes fuertemente conexas.");
		System.out.println("3. Salir");
		System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}
	
	private static void launchMap(String url) {
		
		try {
		URL obj = new URL(url);

		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending GET request to URL: "+url);
		System.out.println("Response code: "+responseCode);
		System.out.println("Content Type: "+con.getContentType());

		//URLImageSource img = (URLImageSource) con.getContent();
		BufferedImage imgb = ImageIO.read(con.getInputStream());
		ImageIcon iIcon = new ImageIcon(imgb.getScaledInstance(imgb.getWidth(), imgb.getHeight(), Image.SCALE_DEFAULT));


		Map map = new Map(iIcon);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
