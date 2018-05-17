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
	
	public Vertex<String,InfoVertex,InfoEdge> verticeMasCongestionado() throws Exception;
	
	public LinkedList<Component<String,InfoVertex,InfoEdge>> calcularComponentesFuertementeConexos() throws Exception;


}
