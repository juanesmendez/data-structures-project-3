package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.LoadHTMLParams;
import com.teamdev.jxbrowser.chromium.internal.ipc.message.GetSpellCheckerLanguageMessage;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import controller.Controller;
import model.algorithms.DepthFirstSearch;
import model.data_structures.DiGraph;
import model.data_structures.Edge;
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
		String scale;


		IDiGraph<String,InfoVertex,InfoEdge> grafo = null; //BORRAR ESTA DECLARACION
		while(!fin)
		{
			printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				//Dentro de este llamado incluyo la carga del archivo .csv con datos de coordenadas que posteriormente seran usadas.
				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();

				
				grafo= Controller.cargarGrafo();
				
				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");
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

					/*
					url = GOOGLE_STATIC_MAPS_API;
					center = "center="+vertex.getValue().getLatitudReferencia()+","+vertex.getValue().getLongitudReferencia();
					zoom = "zoom=15";
					size = "size=600x600";
					scale = "scale=1";
					markers = "markers=size:mid%7C"+vertex.getValue().getLatitudReferencia()+","+vertex.getValue().getLongitudReferencia();
					key = "key="+API_KEY;
					url = url+center+"&"+zoom+"&"+size+"&"+scale+"&"+markers+"&"+key;
					//String url = "https://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&key=AIzaSyDv2q8A1yyFsVxxcPmkWBhebJQrnaANL34";

					launchMap(url);
					 */
					MapManager.dibujoRequerimiento1(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia());

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
						System.out.println("---------------------------------");
					}
					System.out.println("NUMERO TOTAL DE COMPONENTES: "+componentes.size());
					System.out.println("---------------------------------");
					System.out.println("COMPONENTE MAS GRANDE: ");
					System.out.println(componenteMasGrande.toString());
					System.out.println();

					MapManager.dibujoRequerimiento2(componenteMasGrande);

					//------------

					/*
					url = GOOGLE_STATIC_MAPS_API;
					center = "center="+41.881832+","+-87.623177;
					zoom = "zoom=10";
					size = "size=400x400";
					String scale2 = "scale=2";
					markers = "markers=size:tiny%7C";
					String pathComponents = "";
					key = API_KEY;

					for(Integer n:componenteMasGrande.getHashTableVertices().keys()) {
						Vertex<String,InfoVertex,InfoEdge> v = componenteMasGrande.getHashTableVertices().get(n);
						if(v.getValue().getLatitudReferencia() == 0 || v.getValue().getLongitudReferencia() == 0) {
							continue;
						}
						markers = markers+v.getValue().getLatitudReferencia()+","+v.getValue().getLongitudReferencia()+"%7C";
					}
					markers = markers.substring(0, markers.length()-3);
					//System.out.println("markers value: "+markers);

					for(Integer inte:componenteMasGrande.getHashTableVertices().keys()) {
						Vertex<String,InfoVertex,InfoEdge> vert = componenteMasGrande.getHashTableVertices().get(inte);
						for(Edge<InfoEdge> e: vert.getEdges()) {
							Vertex<String,InfoVertex,InfoEdge> finalVer = e.getFinalVertex();

							pathComponents += "path=color:0x0000ff%7Cweight:5%7C" + vert.getValue().getLatitudReferencia() + ","+vert.getValue().getLongitudReferencia() + "%7C" + finalVer.getValue().getLatitudReferencia() + "," + finalVer.getValue().getLongitudReferencia()+"&";
						}
					}

					pathComponents = pathComponents.substring(0, pathComponents.length()-1);
					//System.out.println("Path components: "+pathComponents);
					//url = url+center+"&"+zoom+"&"+size+"&"+markers+"&"+key;
					url = url+size+"&"+scale2+"&"+markers+"&"+key; //Agregar pathcomponents
					//System.out.println("Tamaño url: "+url.length());
					launchMap(url);
					 */
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 3:

				try {
					Iterable<Vertex<String,InfoVertex,InfoEdge>> vertices = Controller.Req3GenerarMapaComponentes();

					//MapManager.dibujoRequerimiento3(vertices);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/*
			       File file2 = new File("mapR2.html");

			       try {
			    	   final Browser browser = new Browser();
					browser.loadURL(file2.toURI().toURL().toString());
					BrowserView browserView = new BrowserView(browser);

				       JFrame frame = new JFrame("JxBrowser Google Maps");
				       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				       frame.add(browserView, BorderLayout.CENTER);
				       frame.setSize(900, 500);
				       frame.setLocationRelativeTo(null);
				       frame.setVisible(true);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 */

				break;
			case 4:
				Path path;
				try {
					path = Controller.encontrarCaminoMenorDistancia();

					System.out.println();
					url = GOOGLE_STATIC_MAPS_API;
					//center = "center="+vertex.getValue().getLatitudReferencia()+","+vertex.getValue().getLongitudReferencia();
					zoom = "zoom=12";
					size = "size=600x600";
					scale = "scale=1";
					markers = "markers=size:mid%7Ccolor:green%7C";
					key = "key="+API_KEY;
					String pathString = "path=color:0x0000ff%7Cweight:5%7C";
					Iterator it = path.getEdges().iterator();
					Vertex<String,InfoVertex,InfoEdge> verticeInicial = null;
					Vertex<String,InfoVertex,InfoEdge> verticeFinal = null;
					if(it.hasNext()) {
						int k=0;
						for(Edge<InfoEdge> e:path.getEdges()) {
							System.out.println(e.getInitialVertex().getNum() + " -> "+ e.getFinalVertex().getNum()+"    "+e.getWeight().getDistancia());

							if(k==0) {
								verticeInicial = e.getInitialVertex();
								Vertex<String,InfoVertex,InfoEdge> initialV = e.getInitialVertex();
								Vertex<String,InfoVertex,InfoEdge> finalV = e.getFinalVertex();

								pathString = pathString+initialV.getValue().getLatitudReferencia()+","+initialV.getValue().getLongitudReferencia()+"%7C"+finalV.getValue().getLatitudReferencia()+","+finalV.getValue().getLongitudReferencia()+"%7C";
								k++;
								continue;
							}

							Vertex<String,InfoVertex,InfoEdge> finalV = e.getFinalVertex();

							pathString = pathString+finalV.getValue().getLatitudReferencia()+","+finalV.getValue().getLongitudReferencia()+"%7C";
							k++;
						}

						Iterator it2 = path.getEdges().iterator();
						while(it2.hasNext()) {
							Edge<InfoEdge> edge = (Edge<InfoEdge>) it2.next();
							if(it2.hasNext() == false) {
								verticeFinal=edge.getFinalVertex();
							}

						}
						System.out.println("-----------------------------------");
						System.out.println("VERTICE INICIAL:");
						System.out.println("Num: "+verticeInicial.getNum());
						System.out.println("Latitud: "+verticeInicial.getValue().getLatitudReferencia());
						System.out.println("Longitud: "+verticeInicial.getValue().getLongitudReferencia());
						System.out.println("-----------------------------------");
						System.out.println("FINAL VERTEX:");
						System.out.println("Num: "+verticeFinal.getNum());
						System.out.println("Latitud: "+verticeFinal.getValue().getLatitudReferencia());
						System.out.println("Longitud: "+verticeFinal.getValue().getLongitudReferencia());

						pathString = pathString.substring(0, pathString.length()-3);
						markers = markers+verticeInicial.getValue().getLatitudReferencia()+","+verticeInicial.getValue().getLongitudReferencia();
						markers = markers+"&"+"markers=size:mid%7Ccolor:red%7C"+verticeFinal.getValue().getLatitudReferencia()+","+verticeFinal.getValue().getLongitudReferencia();
						url = url+"&"+size+"&"+scale+"&"+markers+"&"+pathString+"&"+key;
						System.out.println("-----------------------------------");
						System.out.println("Tiempo estimado: "+path.getSegundos()+" segundos");
						System.out.println("Distancia Estimada: "+path.getDistancia()+" millas");
						System.out.println("Valor estimado: $"+path.getValor());
						System.out.println("-----------------------------------");

						launchMap(url);
					}else {
						System.out.println("No se puede generar camino en el mapa por que no existe conexion entre los vertices aleatorios.");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;

			case 5:
				try {
					Path[] arrayPaths = Controller.caminosMayorMenorDuracion();
					Path path1 = arrayPaths[0];
					Path path2 = arrayPaths[1];
					Vertex<String,InfoVertex,InfoEdge> vi = null;
					Vertex<String,InfoVertex,InfoEdge> vf = null;
					Vertex<String,InfoVertex,InfoEdge> iniV = null;
					Vertex<String,InfoVertex,InfoEdge> finV = null;
					int i=0;

					url = GOOGLE_STATIC_MAPS_API;
					//center = "center="+vertex.getValue().getLatitudReferencia()+","+vertex.getValue().getLongitudReferencia();
					zoom = "zoom=12";
					size = "size=600x600";
					scale = "scale=1";
					String markers1 = "";
					String markers2 = "";
					key = "key="+API_KEY;

					String pathString1 = "";
					String pathString2 = "";

					Iterator iterator1 = path1.getEdges().iterator();
					System.out.println();
					System.out.println("CAMINO 1: ");
					if(iterator1.hasNext()) {
						pathString1 = "path=color:0x0000ff%7Cweight:5%7C";
						markers1 = "markers=size:mid%7Ccolor:green%7C";

						for(Edge<InfoEdge> e: path1.getEdges()) {
							System.out.println(e.getInitialVertex().getNum()+" -> "+e.getFinalVertex().getNum());

							if(i==0) {
								vi = e.getInitialVertex();
								iniV = e.getInitialVertex();
								finV = e.getFinalVertex();

								pathString1 = pathString1+iniV.getValue().getLatitudReferencia()+","+iniV.getValue().getLongitudReferencia()+"%7C"+finV.getValue().getLatitudReferencia()+","+finV.getValue().getLongitudReferencia()+"%7C";


								i++;
								continue;
							}

							finV = e.getFinalVertex();

							pathString1 = pathString1+finV.getValue().getLatitudReferencia()+","+finV.getValue().getLongitudReferencia()+"%7C";
							i++;
						}

						pathString1 = pathString1.substring(0, pathString1.length()-3);
						Iterator iterator2 = path1.getEdges().iterator();
						while(iterator2.hasNext()) {
							Edge<InfoEdge> edge = (Edge<InfoEdge>) iterator2.next();
							if(iterator2.hasNext() == false) {
								vf=edge.getFinalVertex();
							}
						}
						System.out.println("------------------------------------");
						System.out.println("VERTICE INICIAL:");
						System.out.println("\tNum: "+vi.getNum());
						System.out.println("\tLatitud: "+vi.getValue().getLatitudReferencia());
						System.out.println("\tLongitud: "+vi.getValue().getLongitudReferencia());
						System.out.println("------------------------------------");
						System.out.println("FINAL VERTEX:");
						System.out.println("\tNum: "+vf.getNum());
						System.out.println("\tLatitud: "+vf.getValue().getLatitudReferencia());
						System.out.println("\tLongitud: "+vf.getValue().getLongitudReferencia());

						markers1 = markers1+vi.getValue().getLatitudReferencia()+","+vi.getValue().getLongitudReferencia();
						markers1 = markers1+"&"+"markers=size:mid%7Ccolor:red%7C"+vf.getValue().getLatitudReferencia()+","+vf.getValue().getLongitudReferencia();

						System.out.println("------------------------------------");
						System.out.println("Tiempo estimado: "+path1.getSegundos()+" segundos");
						System.out.println("Distancia Estimada: "+path1.getDistancia()+" millas");
						System.out.println("Valor estimado: $"+path1.getValor());
						System.out.println("------------------------------------");
					}else {
						System.out.println("------------------------------------");
						System.out.println("No existe un camino del vertice inicial al vertice final.");
					}

					Iterator iterator3 = path2.getEdges().iterator();
					System.out.println();
					if(iterator3.hasNext()) {

						pathString2 = "path=color:red%7Cweight:5%7C";
						markers2 = "markers=size:mid%7Ccolor:blue%7C";
						i=0;
						System.out.println("CAMINO 2");
						for(Edge<InfoEdge> e:path2.getEdges()) {
							System.out.println(e.getInitialVertex().getNum()+" -> "+e.getFinalVertex().getNum());
							if(i==0) {
								vi = e.getInitialVertex();
								iniV = e.getInitialVertex();
								finV = e.getFinalVertex();

								pathString2 = pathString2+iniV.getValue().getLatitudReferencia()+","+iniV.getValue().getLongitudReferencia()+"%7C"+finV.getValue().getLatitudReferencia()+","+finV.getValue().getLongitudReferencia()+"%7C";


								i++;
								continue;
							}

							finV = e.getFinalVertex();

							pathString2 = pathString2+finV.getValue().getLatitudReferencia()+","+finV.getValue().getLongitudReferencia()+"%7C";
							i++;
						}
						pathString2 = pathString2.substring(0, pathString2.length()-3);

						Iterator iterator4 = path2.getEdges().iterator();
						while(iterator4.hasNext()) {
							Edge<InfoEdge> edge = (Edge<InfoEdge>) iterator4.next();
							if(iterator4.hasNext() == false) {
								vf=edge.getFinalVertex();
							}
						}
						System.out.println("------------------------------------");
						System.out.println("VERTICE INICIAL:");
						System.out.println("\tNum: "+vi.getNum());
						System.out.println("\tLatitud: "+vi.getValue().getLatitudReferencia());
						System.out.println("\tLongitud: "+vi.getValue().getLongitudReferencia());
						System.out.println("------------------------------------");
						System.out.println("FINAL VERTEX:");
						System.out.println("\tNum: "+vf.getNum());
						System.out.println("\tLatitud: "+vf.getValue().getLatitudReferencia());
						System.out.println("\tLongitud: "+vf.getValue().getLongitudReferencia());
						System.out.println("------------------------------------");

						markers2 = markers2 +vi.getValue().getLatitudReferencia()+","+vi.getValue().getLongitudReferencia();
						markers2 = markers2 +"&"+"markers=size:mid%7Ccolor:yellow%7C"+vf.getValue().getLatitudReferencia()+","+vf.getValue().getLongitudReferencia();

						System.out.println("Tiempo estimado: "+path2.getSegundos()+" segundos");
						System.out.println("Distancia Estimada: "+path2.getDistancia()+" millas");
						System.out.println("Valor estimado: $"+path2.getValor());
						System.out.println("------------------------------------");
					}else {
						System.out.println("------------------------------------");
						System.out.println("No existe un camino del vertice final al vertice inicial.");
					}

					if(iterator1.hasNext() && iterator3.hasNext()) {
						url = url + size +"&"+ scale +"&"+ markers1 +"&"+ markers2 + "&"+pathString1 +"&" +pathString2 + "&"+key;
						launchMap(url);
					}else if(iterator1.hasNext() && !iterator3.hasNext()) {
						url = url + size +"&"+ scale +"&"+ markers1 +"&"+pathString1 +"&"+key;
						launchMap(url);
					}else if(!iterator1.hasNext() && iterator3.hasNext()) {
						url = url + size +"&"+ scale +"&"+ markers2 +"&"+pathString2 +"&"+key;
						launchMap(url);
					}

				}catch(Exception e) {
					e.printStackTrace();
				}
				break;

			case 6:

				try {
					LinkedList<Path> paths = Controller.Req6CaminosSinPeaje();
				}catch(Exception e) {
					e.printStackTrace();
				}

				break;

			case 7:
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
		System.out.println("1. Obtener vertice mas congestionado en Chicago");
		System.out.println("2. Obtener componentes fuertemente conexos.");
		System.out.println("3. Generar mapa coloreado de la red vial de Chicago en Google Maps");
		System.out.println("4. Encontrar camino de menor distancia para dos puntos aleatorios.");
		System.out.println("5. Hallar caminos de mayor y menor duracion entre dos puntos aleatorios");
		System.out.println("6. Encontrar caminos sin peaje entre dos puntos aleatorios");
		System.out.println("7. Salir");
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
