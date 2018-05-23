package model.logic;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.javafx.binding.StringFormatter;

import api.ITaxiTripsManager;
import javafx.util.converter.LocalDateStringConverter;
import model.algorithms.AllPaths;
import model.algorithms.DijkstraSP;
import model.algorithms.FindAllPaths;
import model.algorithms.FindAllPathsGeneric;
import model.algorithms.KosarajuSharirSCC;
import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.IDiGraph;
import model.data_structures.IHashTableLP;
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.data_structures.Vertex;
import model.vo.Component;
import model.vo.Coordinate;
import model.vo.InfoEdge;
import model.vo.InfoVertex;
import model.vo.Path;
import view.MapManager;

public class TaxiTripsManager implements ITaxiTripsManager
{

	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large.json";
	public static final String DIRECCION_LARGE_JSON_DIA_1 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-02-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_2 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-03-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_3 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-04-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_4 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-05-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_5 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-06-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_6 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-07-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_7 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-08-02-2017.json";
	public static final String DIRECCION_CHICAGO_STREETS = "./data/chicago-streets.csv";


	public static final String GOOGLE_STATIC_MAPS_API = "https://maps.googleapis.com/maps/api/staticmap?";
	public static final String API_KEY = "AIzaSyDv2q8A1yyFsVxxcPmkWBhebJQrnaANL34";


	private IDiGraph<String, InfoVertex, InfoEdge> graph;
	private LinkedList<Coordinate> coordinatesList;
	private LinkedList<Component<String,InfoVertex,InfoEdge>> componentsList;

	public TaxiTripsManager() {

	}

	@Override
	public IDiGraph<String,InfoVertex,InfoEdge> cargarGrafo() {
		// TODO Auto-generated method stub
		this.graph = new DiGraph<>();

		JSONParser parser = new JSONParser();


		try {
			Object obj = parser.parse(new FileReader("./data/graph.json"));


			JSONArray jsonArray = (JSONArray)obj;

			Iterator<JSONObject> iterator = jsonArray.iterator();

			while(iterator.hasNext()) {
				JSONObject object = iterator.next();

				String key = (String) object.get("key");
				//System.out.println((String)object.get("latitud_referencia"));

				JSONObject value = (JSONObject) object.get("value");
				double latitudReferencia = (double) value.get("latitud_referencia");
				double longitudReferencia = (double) value.get("longitud_referencia");
				//------ Me aseguro que no se cree el vertice 0,0
				if(latitudReferencia == 0 || longitudReferencia == 0) {
					continue;
				}


				InfoVertex infoVertex = new InfoVertex(latitudReferencia, longitudReferencia);

				JSONArray arrayServicios = (JSONArray)value.get("servicios");
				LinkedList<String> listaServicios = new List<>();
				if(arrayServicios !=null) {
					Iterator<JSONObject> iteratorServicios = arrayServicios.iterator();
					int i=0;
					while(iteratorServicios.hasNext()) {
						String idServicio = String.valueOf(iteratorServicios.next());
						listaServicios.add(idServicio);
						i++;
					}
				}
				infoVertex.setListaServicios(listaServicios);

				graph.addVertex(key, infoVertex);
			}
			//Ahora leo los arcos

			iterator = jsonArray.iterator();
			while(iterator.hasNext()) {
				JSONObject object = iterator.next();

				String keyVertex = (String) object.get("key");
				JSONArray edgesArray = (JSONArray) object.get("edges");
				if(edgesArray != null) {
					Iterator<JSONObject> it = edgesArray.iterator();
					while(it.hasNext()) {
						JSONObject edge = it.next();
						InfoEdge infoEdge;
						//----
						/*
						float distancia = Float.valueOf((String)edge.get("distancia"));
						float valor = Float.valueOf((String)edge.get("valor"));
						int contadorPeaje = Integer.parseInt((String)edge.get("contador_peaje"));
						int segundos = Integer.parseInt((String)edge.get("segundos"));*/
						//-----

						JSONObject value = (JSONObject) object.get("value");
						double latitudReferencia = (double) value.get("latitud_referencia");
						double longitudReferencia = (double) value.get("longitud_referencia");
						//------ Me aseguro que no se creen los arcos del vertice 0,0
						if(latitudReferencia == 0 || longitudReferencia == 0) {
							continue;
						}


						double aux = (double) edge.get("distancia");
						float distancia = (float)aux;
						aux = (double) edge.get("valor");
						float valor = (float)aux;
						long auxLong = (long) edge.get("contador_peaje");
						int contadorPeaje = (int)auxLong;
						auxLong = (long)edge.get("segundos");
						int segundos = (int)auxLong;

						infoEdge = new InfoEdge(distancia, valor, segundos, contadorPeaje);

						String keyInitialVertex = (String) edge.get("key_initial_vertex");
						String keyFinalVertex = (String) edge.get("key_final_vertex");

						graph.addEdge(keyInitialVertex, keyFinalVertex, infoEdge);

					}

				}

			}



		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally {

		}

		//Cargar .csv con las calles

		this.cargarChicagoStreets();

		return this.graph;
	}

	@Override
	public void cargarChicagoStreets() {
		// TODO Auto-generated method stub
		this.coordinatesList = new List<>();
		String fileName = DIRECCION_CHICAGO_STREETS;
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNext()) {
				String data = inputStream.nextLine();	
				String[] values = data.split(";");
				for(int i=6;i<values.length;i++) { // Empieza en 6 por que solo me interesan las coordenadas.
					String valor = values[i];
					if(valor.equals("")) { //Si no hay coordenadas, simplemente ignorar
						continue;
					}
					String[] coordinate = valor.split(" ");
					if(coordinate.length == 3) {
						String latitud = coordinate[2];
						String longitud = coordinate[1];
						coordinatesList.add(new Coordinate(Double.valueOf(latitud), Double.valueOf(longitud)));

					}
					if(coordinate.length == 2) {
						String latitud = coordinate[1];
						String longitud = coordinate[0];
						coordinatesList.add(new Coordinate(Double.valueOf(latitud), Double.valueOf(longitud)));
					}
				}
			}
			inputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public Vertex<String, InfoVertex, InfoEdge> verticeMasCongestionado() throws Exception{
		// TODO Auto-generated method stub
		Vertex<String,InfoVertex,InfoEdge> vertexToFind=null;

		int mayor = Integer.MIN_VALUE;
		int total;

		if(this.graph == null) {
			throw new Exception("Grafo aun no ha sido creado");
		}
		for(Vertex<String,InfoVertex,InfoEdge> v:this.graph.getListVertices()) {
			total = v.getInDegree() + v.getOutDegree();
			if(total > mayor) {
				mayor = total;
				vertexToFind = v;
			}
		}

		return vertexToFind;
	}

	@Override
	public LinkedList<Component<String,InfoVertex,InfoEdge>> calcularComponentesFuertementeConexos() throws Exception {
		// TODO Auto-generated method stub
		LinkedList<Component<String,InfoVertex,InfoEdge>> listComponents = new List<Component<String,InfoVertex,InfoEdge>>();
		Component<String,InfoVertex,InfoEdge> comp;
		Component<String,InfoVertex,InfoEdge> aux;

		if(this.graph == null) {
			throw new Exception("Grafo aun no ha sido creado");
		}
		KosarajuSharirSCC<String, InfoVertex, InfoEdge> kosarajuSharir = new KosarajuSharirSCC<>(graph);

		int[] array = kosarajuSharir.getId();

		for(int i=0;i<array.length;i++) {
			//System.out.println(array[i]);
			int color = array[i];
			comp = new Component<String,InfoVertex,InfoEdge>(color);
			aux = listComponents.get(comp);

			if(aux == null) {
				aux = comp;
				aux.agregarVertice();
				aux.addVertex(this.graph.getVertexByNum(i));
				listComponents.add(aux);
			}else {
				aux.agregarVertice();
				aux.addVertex(this.graph.getVertexByNum(i));
			}
		}
		//System.out.println("ARRAY SIZE: "+array.length);


		this.componentsList = listComponents;

		return listComponents;
	}

	@Override
	public Iterable<Vertex<String,InfoVertex,InfoEdge>> Req3GenerarMapaComponentes() throws Exception {
		// TODO Auto-generated method stub
		if(this.graph == null) {
			throw new Exception("Grafo aun no ha sido creado");
		}
		if(this.componentsList == null) {
			throw new Exception("No se han calculado componentes conexos del grafo.");
		}
		
		int totalServicios = 0;
		//Calcular total servicios
		for(Vertex<String,InfoVertex,InfoEdge> v:this.graph.getListVertices()) {
			totalServicios += v.getValue().getListaServicios().size();
		}
		
		//Calcular porcentaje servicios de cada vertice
		for(Vertex<String,InfoVertex,InfoEdge> v:this.graph.getListVertices()) {
			//System.out.println(v.getValue().getListaServicios().size());
			int numServ = v.getValue().getListaServicios().size();
			float porcentajeServ = (float)numServ /totalServicios;
			v.getValue().setPorcentajeServicios(porcentajeServ);
		}
		
		
		MapManager.dibujoRequerimiento3(this.graph.getListVertices(), this.componentsList);
		
		/*
		String url = GOOGLE_STATIC_MAPS_API;
		String size = "size=400x400";
		String color = "color:";
		String markersTotal = "";
		String markers = "markers=size:mid%7C";
		String key = API_KEY;
		
		
		Random rand = new Random();
		
		for(Component c:this.componentsList) {
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			//System.out.println("R: "+r+" G: "+g+" B: "+b);
			Color randomColor = new Color(r, g, b);
			
			String hexColour = Integer.toHexString(randomColor.getRGB() & 0xffffff);
			if (hexColour.length() < 6) {
				hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
			}
			hexColour =  "0x" + hexColour;
			//System.out.println(hexColour);
			//-----------------------
			markers = "markers=size:mid%7C";
			color = "color:"+hexColour+"%7C";
			
			markers = markers+color;
			IHashTableLP<Integer,Vertex<String,InfoVertex,InfoEdge>> hash = c.getHashTableVertices();
			for(Integer i:hash.keys()) {
				Vertex<String,InfoVertex,InfoEdge> v = hash.get(i);
				markers += v.getValue().getLatitudReferencia() + ","+v.getValue().getLongitudReferencia() + "%7C";
			}
			markers = markers.substring(0, markers.length()-3);
			
			if(markersTotal.equals("")) {
				markersTotal += markers;
			}else {
				markersTotal += "&" + markers;
			}
		}
		
		System.out.println(markersTotal);
		
		url = url+"&"+size+"&"+markersTotal+"&"+key;
		System.out.println("Size url: "+url.length());*/
		
		return this.graph.getListVertices();
	}

	@Override
	public Path encontrarCaminoMenorDistancia() {
		// TODO Auto-generated method stub
		Path path;
		Random randomGenerator = new Random();
		int index1 = randomGenerator.nextInt(this.coordinatesList.size());
		int index2 = randomGenerator.nextInt(this.coordinatesList.size());

		Coordinate initialCoordinate = this.coordinatesList.get(index1);
		Coordinate finalCoordinate = this.coordinatesList.get(index2);

		//Recorrer vertices y encontrar vertice mas cercano a cada uno
		double menor1 = Integer.MAX_VALUE;
		double menor2 = Integer.MAX_VALUE;

		double distance1;
		double distance2;
		Vertex<String,InfoVertex,InfoEdge> initialVertex=null;
		Vertex<String,InfoVertex,InfoEdge> finalVertex=null;

		for(Vertex<String,InfoVertex,InfoEdge> vertex:graph.getListVertices()) {

			distance1 = getHarvesianDistance(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia(), initialCoordinate.getLatitude(), initialCoordinate.getLongitude());
			distance2 = getHarvesianDistance(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia(), finalCoordinate.getLatitude(), finalCoordinate.getLongitude());
			if(distance1 < menor1) {
				menor1 = distance1;
				initialVertex = vertex;
			}
			if(distance2 < menor2) {
				menor2 = distance2;
				finalVertex = vertex;
			}

		}
		//Crear dijstra apartir del vertice inicial

		DijkstraSP<String, InfoVertex, InfoEdge> dijkstra = new DijkstraSP<>(this.graph, initialVertex, "distancia");
		Iterable<Edge<InfoEdge>> pathIterable = dijkstra.pathTo(finalVertex);
		path = new Path(pathIterable); //Lo meto en un objeto de tipo Path


		return path;
	}

	@Override
	public Path[] caminosMayorMenorDuracion() {
		// TODO Auto-generated method stub
		Path[] paths = new Path[2];
		Path path1;
		Path path2;
		//Iterable<Edge<InfoEdge>>[] array = new Iterable<Edge<InfoEdge>>[2];
		Random randomGenerator = new Random();
		int index1 = randomGenerator.nextInt(this.coordinatesList.size());
		int index2 = randomGenerator.nextInt(this.coordinatesList.size());

		Coordinate initialCoordinate = this.coordinatesList.get(index1);
		Coordinate finalCoordinate = this.coordinatesList.get(index2);

		//Recorrer vertices y encontrar vertice mas cercano a cada uno
		double menor1 = Integer.MAX_VALUE;
		double menor2 = Integer.MAX_VALUE;

		double distance1;
		double distance2;
		Vertex<String,InfoVertex,InfoEdge> initialVertex=null;
		Vertex<String,InfoVertex,InfoEdge> finalVertex=null;

		Iterable<Edge<InfoEdge>> aux;
		DijkstraSP<String, InfoVertex, InfoEdge> dijkstra;

		for(Vertex<String,InfoVertex,InfoEdge> vertex:graph.getListVertices()) {

			distance1 = getHarvesianDistance(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia(), initialCoordinate.getLatitude(), initialCoordinate.getLongitude());
			distance2 = getHarvesianDistance(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia(), finalCoordinate.getLatitude(), finalCoordinate.getLongitude());
			if(distance1 < menor1) {
				menor1 = distance1;
				initialVertex = vertex;
			}
			if(distance2 < menor2) {
				menor2 = distance2;
				finalVertex = vertex;
			}

		}

		dijkstra = new DijkstraSP<>(this.graph, initialVertex, "duracion");

		aux = dijkstra.pathTo(finalVertex);
		path1 = new Path(aux);
		paths[0] = path1;

		dijkstra = new DijkstraSP<>(this.graph, finalVertex, "duracion");
		aux = dijkstra.pathTo(initialVertex);
		path2 = new Path(aux);
		paths[1] = path2;


		return paths;
	}

	@Override
	public LinkedList<Path> Req6CaminosSinPeaje() {
		// TODO Auto-generated method stub

		LinkedList<Path> respuesta = new List<>();
		
		Random randomGenerator = new Random();
		int index1 = randomGenerator.nextInt(this.coordinatesList.size());
		int index2 = randomGenerator.nextInt(this.coordinatesList.size());

		Coordinate initialCoordinate = this.coordinatesList.get(index1);
		Coordinate finalCoordinate = this.coordinatesList.get(index2);

		//Recorrer vertices y encontrar vertice mas cercano a cada uno
		double menor1 = Integer.MAX_VALUE;
		double menor2 = Integer.MAX_VALUE;

		double distance1;
		double distance2;
		Vertex<String,InfoVertex,InfoEdge> initialVertex=null;
		Vertex<String,InfoVertex,InfoEdge> finalVertex=null;

		for(Vertex<String,InfoVertex,InfoEdge> vertex:graph.getListVertices()) {

			distance1 = getHarvesianDistance(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia(), initialCoordinate.getLatitude(), initialCoordinate.getLongitude());
			distance2 = getHarvesianDistance(vertex.getValue().getLatitudReferencia(), vertex.getValue().getLongitudReferencia(), finalCoordinate.getLatitude(), finalCoordinate.getLongitude());
			if(distance1 < menor1) {
				menor1 = distance1;
				initialVertex = vertex;
			}
			if(distance2 < menor2) {
				menor2 = distance2;
				finalVertex = vertex;
			}

		}
		System.out.println(initialVertex.getNum());
		System.out.println(finalVertex.getNum());

		KosarajuSharirSCC<String, InfoVertex, InfoEdge> kos = new KosarajuSharirSCC<>(this.graph);
		int[] idArray = kos.getId();



		//initialVertex = graph.getVertexByNum(114);
		//finalVertex = graph.getVertexByNum(130);

		FindAllPaths<String, InfoVertex> fal = new FindAllPaths<String,InfoVertex>(this.graph, initialVertex, finalVertex,idArray);

		LinkedList<Path> listaPaths = fal.getPaths();
		
		for (Path p:listaPaths)
		{
			if(p.getPeaje()==true)
			{
				respuesta.add(p);
			}
		}

		//114
		//130


		/*
		FindAllPathsGeneric<String, InfoVertex, InfoEdge> falg =  new FindAllPathsGeneric<>(this.graph, initialVertex, finalVertex, idArray);
		LinkedList<Path>listaPaths = null;*/
		/*
		AllPaths<String, InfoVertex> allPaths = new AllPaths<>(this.graph, initialVertex, finalVertex);

		LinkedList<Path> listaPaths = allPaths.getPaths();*/
		return respuesta;
	}

	public static double getHarvesianDistance(double lat1, double lon1, double lat2, double lon2)
	{
		// TODO Auto-generated method stub
		final int R = 6371*1000; // Radious of the earth in meters
		Double latDistance = Math.toRadians(lat2-lat1);
		Double lonDistance = Math.toRadians(lon2-lon1);
		Double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance/2) * Math.sin(lonDistance/2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		return distance;
	}







}