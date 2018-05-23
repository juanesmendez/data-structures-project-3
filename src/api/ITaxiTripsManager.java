package api;


import model.data_structures.*;
import model.vo.*;
/**
 * API para la clase de logica principal  
 */
public interface ITaxiTripsManager 
{
	
	public IDiGraph<String,InfoVertex,InfoEdge> cargarGrafo();
	
	public void cargarChicagoStreets();
	/**
	 * Mostrar en la consola de texto la informaci�n del v�rtice m�s congestionado en Chicago 
	 * (aquel que contiene la mayor cantidad de servicios que salen y llegan a �l): su (latitud, 
	 * longitud), total servicios que salieron y total de servicios que llegaron.
	 * Visualizaci�n: marque la localizaci�n del v�rtice res
	 * ultante en un mapa en Google Maps.
	 */
	public Vertex<String,InfoVertex,InfoEdge> verticeMasCongestionado() throws Exception;
	
	/**
	 * Calcule los componentes fuertemente conexos presentes en el grafo. As�gnele un color 
	 * a los v�rtices que componen un componente.  Retorne una lista en donde en cada nodo, 
	 * se tiene la informaci�n de un componente conexo (color, n�mero de v�rtices que lo 
	 * componen).   Muestre en la consola de texto el total de componentes identificadas y la 
	 * informaci�n de la lista de componentes conexo.
	 */
	public LinkedList<Component<String,InfoVertex,InfoEdge>> calcularComponentesFuertementeConexos() throws Exception;
	
	/**
	 * A  partir  del  grafo  cargado  al  inicio y  de  los  componentes  conectados 
	 * encontrados  en  el  punto  2, genere  un  mapa  coloreado  de  la  red  vial  
	 * de  Chicago utilizando Google Maps.
	 */
	public Iterable<Vertex<String,InfoVertex,InfoEdge>> Req3GenerarMapaComponentes() throws Exception;
	
	/**
	 *Encontrar el camino de costo m�nimo (menor distancia)  para un servicio que inicia 
	 *en un  punto  (latitud,  longitud)  escogido  aleatoriamente  de  la  informaci�n  
	 *cargada  del archivo  de  calles  (StreetLines.csv)  y  finaliza  en  
	 *un  punto  (latitud,  longitud)  escogido tambi�n de manera aleatoria del archivo de calles.  
	 */
	public Path encontrarCaminoMenorDistancia();
	
	/**
	 * Dado un servicio que inicia en un punto (latitud, longitud) escogido aleatoriamente de la 
	 * informaci�n  cargada  del  archivo  de  calles  (StreetLines.csv)  y  finaliza  
	 * en  un  punto (latitud,  longitud)  escogido  tambi�n  de  manera  aleatoria  del  
	 * archivo  de  calles.  Aproxime los puntos de inicio y fin a los v�rtices m�s cercanos 
	 * en el grafo y encuentre los caminos de mayor y menor duraci�n entre dichos puntos. 
	 */
	public Path[] caminosMayorMenorDuracion();
	
	/**
	 * Dado un servicio que inicia en un punto (latitud, longitud) escogido aleatoriamente 
	 * de la  informaci�n  cargada  del  archivo  de  calles  (StreetLines.csv)  y  finaliza  
	 * en  un  punto (latitud, longitud)escogido tambi�n de manera aleatoria del archivo de calles.  
	 * Indique si  existen  caminos  entre  ambos  puntos,  en  los  que  no  deba  pagar  peaje. 
	 */
	public LinkedList<Path> Req6CaminosSinPeaje();

}
