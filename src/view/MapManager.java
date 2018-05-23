package view;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import model.data_structures.Edge;
import model.data_structures.IHashTableLP;
import model.data_structures.LinkedList;
import model.data_structures.Vertex;
import model.vo.*;

public class MapManager {

	public static void dibujoRequerimiento1(double lat, double lon){
		
		try {
			File htmlTemplateFile = new File("map.html");
			String htmlString;
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
			String zoomTag = "15";
			String scriptTag = "var myLatLng = {lat: "+lat+", lng: "+lon+"};" + 
					"var marker = new google.maps.Marker({" + 
					"    position: myLatLng," + 
					"    map: map," + 
					"    title: 'Vertice mas congestionado'" + 
					"  });";
			htmlString = htmlString.replace("//zoom", zoomTag);
			htmlString = htmlString.replace("//$script", scriptTag);
			
			File newHtmlFile = new File("mapR1"+".html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);	
			
			
			File f = new File("mapR1"+".html");
	        java.awt.Desktop.getDesktop().browse(f.toURI());
			
			//MapBrowser mapBrowser =  new MapBrowser("mapR1"+".html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void dibujoRequerimiento2(Component<String,InfoVertex,InfoEdge> component) {
		
		try {
			File htmlTemplateFile = new File("map.html");
			String htmlString;
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
			String zoomTag = "11";
			String tagArrayCoordinates = "var markersCoordinates = [";
			String tagLoop = "for(var i = 0; i < markersCoordinates.length; i++) {\n"
					+ "var obj = markersCoordinates[i];\n"
					+ "var lati = obj.lat;\n" //obj['lat'];\n
					+ "var longitud = obj.lng;\n"
					+ "var latLng = new google.maps.LatLng(lati,longitud);\n"
					+ "var marker = new google.maps.Marker({\n"
					+ "position: latLng,\n"
					+ "map: map\n"
					+ "});\n"
					+ "}\n"; //CHEQUEAR SI E L \N SIRVE
			String tagArrayPathsDeclaration = "var arrayPaths = [];";
			String tagArrayPaths = "\nvar path;";
			String tagLoopPaths = "for(var j = 0; j < arrayPaths.length; j++) {\n"
					+ "var p = arrayPaths[j];\n"
					+ "var pathObject = new google.maps.Polyline({\n"
					+ "path: p,\n"
					+ "geodesic: true,\n"
					+ "strokeColor: '#3399FF',\n"
					+ "strokeOpacity: 0.1,\n"
					+ "strokeWeight: 2,\n"
					+ "});\n"
					+ "pathObject.setMap(map);\n"
					+ "}";
			
			IHashTableLP<Integer,Vertex<String,InfoVertex,InfoEdge>> hash = component.getHashTableVertices();
			
			//Recorro coordenadas de los marcadores:
			for(Integer key:hash.keys()) {
				Vertex<String,InfoVertex,InfoEdge> v = hash.get(key);
				tagArrayCoordinates += "\n{lat: "+v.getValue().getLatitudReferencia()+","+"lng: "+v.getValue().getLongitudReferencia()+"},";
			}
			tagArrayCoordinates = tagArrayCoordinates.substring(0, tagArrayCoordinates.length()-1);
			tagArrayCoordinates += "];";
			
			// Recorro para crear las coordenadas de los paths
			for(Integer key:hash.keys()) {
				Vertex<String,InfoVertex,InfoEdge> v = hash.get(key);
				for(Edge<InfoEdge> e:v.getEdges()) {
					Vertex<String,InfoVertex,InfoEdge> vi = e.getInitialVertex();
					Vertex<String,InfoVertex,InfoEdge> vf = e.getFinalVertex();
					tagArrayPaths += "\npath = [\n"
							+ "{lat: "+vi.getValue().getLatitudReferencia()+","+"lng: "+vi.getValue().getLongitudReferencia()+"},\n"
					+"{lat: "+vf.getValue().getLatitudReferencia() + ","+ "lng: "+vf.getValue().getLongitudReferencia()+"}\n"
							+ "];\n"
							+ "arrayPaths.push(path);";
				}
			}
			
			
			
			htmlString = htmlString.replace("//zoom", zoomTag);
			htmlString = htmlString.replace("//arrayCoordinates", tagArrayCoordinates);
			htmlString = htmlString.replace("//loop", tagLoop);
			
			htmlString = htmlString.replace("//arrayPathsDeclaration", tagArrayPathsDeclaration);
			htmlString = htmlString.replace("//arrayPaths", tagArrayPaths);
			htmlString = htmlString.replace("//cicloCamino", tagLoopPaths);
			//htmlString = htmlString.replace("//$script", scriptTag);
			
			File newHtmlFile = new File("mapR2"+".html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);	
			
			
			File f = new File("mapR2"+".html");
	        java.awt.Desktop.getDesktop().browse(f.toURI());
			//MapBrowser mapBrowser =  new MapBrowser("mapR2"+".html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void dibujoRequerimiento3(Iterable<Vertex<String,InfoVertex,InfoEdge>> vertices, LinkedList<Component<String,InfoVertex,InfoEdge>> components) {
		
		try {
			File htmlTemplateFile = new File("map.html");
			String htmlString;
			htmlString = FileUtils.readFileToString(htmlTemplateFile);
			//-------------
			String zoomTag = "11";
			String tagDeclaracionArregloCirculos = "var arrayCircles = [];\n";
			String tagDeclaracionArregloCaminos = "var arrayPaths = [];\n";
			String tagArregloCirculos = "var circle;\n";
			String tagArregloCaminos = "var path;\n";
			
			for(Vertex<String,InfoVertex,InfoEdge> v:vertices) {
				tagArregloCirculos += "\ncircle = {\n"
						+ "center: "+"{lat: "+v.getValue().getLatitudReferencia() + "," + " lng: "+v.getValue().getLongitudReferencia() + "},\n"
						+ "color: "+"'"+v.getColor() + "'" + ",\n"
						+ "porcentaje: "+ v.getValue().getPorcentajeServicios()+"\n"
						+ "};\n"
						+ "arrayCircles.push(circle);\n";
			}
			String tagRecorrido = "for(var i = 0; i < arrayCircles.length; i++) {\n"
					+ "var objeto = arrayCircles[i];\n"
					+ "var mapCircle = new google.maps.Circle({\n"
					+ "strokeColor: objeto.color,\n"
					+ "strokeOpacity: 0.8,\n"
					+ "strokeWeight: 2,\n"
					+ "fillColor: objeto.color,\n"
					+ "fillOpacity: 0.20,\n"
					+ "map: map,\n"
					+ "center: objeto.center,\n"
					+ "radius: objeto.porcentaje * 10000\n"
					+ "});\n"
					+ "}\n";
			
			String tagRecorridoCaminos = "for(var j = 0; j < arrayPaths.length; j++) {\n"
					+ "var obj = arrayPaths[j];\n"
					+ "var pathMap = new google.maps.Polyline({\n"
					+ "path: obj.coordinates,\n"
					+ "geodesic: true,\n"
					+ "strokeColor: obj.color,\n"
					+ "strokeOpacity: 0.1,\n"
					+ "strokeWeight: 2\n"
					+ "});\n"
					+ "pathMap.setMap(map);\n"
					+ "}\n";
			
			for(Vertex<String,InfoVertex,InfoEdge> v:vertices) {
				for(Edge<InfoEdge> e:v.getEdges()) {
					Vertex<String,InfoVertex,InfoEdge> vi = e.getInitialVertex();
					Vertex<String,InfoVertex,InfoEdge> vf = e.getFinalVertex();
					tagArregloCaminos += "\npath = {\n"
							+ "coordinates: ["+ "{lat: "+vi.getValue().getLatitudReferencia()+","+"lng: "+vi.getValue().getLongitudReferencia()+"},\n"
							+"{lat: "+vf.getValue().getLatitudReferencia() + ","+ "lng: "+vf.getValue().getLongitudReferencia()+"}],\n"
									+ "color: "+"'"+vi.getColor() + "'" + "\n"
											+ "};\n"
											+ "arrayPaths.push(path);\n";		
				}
			}
			
			
			
			htmlString =  htmlString.replace("//zoom", zoomTag);
			htmlString = htmlString.replace("//declaracionArregloCirculos", tagDeclaracionArregloCirculos);
			htmlString = htmlString.replace("//arregloCirculos", tagArregloCirculos);
			htmlString = htmlString.replace("//recorrido", tagRecorrido);

			htmlString = htmlString.replace("//declaracionArregloCaminos", tagDeclaracionArregloCaminos);
			htmlString = htmlString.replace("//llenarElementos", tagArregloCaminos);
			htmlString = htmlString.replace("//lopez", tagRecorridoCaminos);
			
			
			//--------------
			File newHtmlFile = new File("mapR3"+".html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);	
			
			
			File f = new File("mapR3"+".html");
	        java.awt.Desktop.getDesktop().browse(f.toURI());
			
			//MapBrowser mapBrowser =  new MapBrowser("mapR3"+".html");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
