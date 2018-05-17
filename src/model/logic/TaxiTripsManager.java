package model.logic;

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
import java.util.Scanner;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.javafx.binding.StringFormatter;

import api.ITaxiTripsManager;
import javafx.util.converter.LocalDateStringConverter;
import model.algorithms.KosarajuSharirSCC;
import model.data_structures.DiGraph;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.data_structures.Vertex;
import model.vo.Component;
import model.vo.Coordinate;
import model.vo.InfoEdge;
import model.vo.InfoVertex;

import com.google.*;
import com.google.maps.model.Bounds;
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



	private IDiGraph<String, InfoVertex, InfoEdge> graph;
	private LinkedList<Coordinate> coordinatesList;

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
						String latitud = coordinate[1];
						String longitud = coordinate[2];
						coordinatesList.add(new Coordinate(Double.valueOf(latitud), Double.valueOf(longitud)));
						
					}
					if(coordinate.length == 2) {
						String latitud = coordinate[0];
						String longitud = coordinate[1];
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
			System.out.println(array[i]);
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
		System.out.println("ARRAY SIZE: "+array.length);



		return listComponents;
	}


}