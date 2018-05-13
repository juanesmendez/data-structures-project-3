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
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.javafx.binding.StringFormatter;

import api.ITaxiTripsManager;
import javafx.util.converter.LocalDateStringConverter;
import model.data_structures.DiGraph;
import model.data_structures.IDiGraph;
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.vo.InfoEdge;
import model.vo.InfoVertex;
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

	private IDiGraph<String, InfoVertex, InfoEdge> graph;


	public TaxiTripsManager() {

		graph = new DiGraph<>();

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

		return this.graph;
	}
}