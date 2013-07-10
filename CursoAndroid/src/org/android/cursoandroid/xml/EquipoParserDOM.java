package org.android.cursoandroid.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EquipoParserDOM {
	
	private InputStream fileInputStream;
	
	public EquipoParserDOM(InputStream fileInputStream) {
		try {
			this.fileInputStream = fileInputStream;
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Equipo> parse() {
		//Instanciamos la fábrica para DOM
		DocumentBuilderFactory factory =
		DocumentBuilderFactory.newInstance();
		List<Equipo> Equipos = new ArrayList<Equipo>();

		try {
			//Creamos un nuevo parser DOM
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Realizamos lalectura completa del XML
			Document dom = builder.parse(this.fileInputStream);
			//Nos situamos en el nodo principal del árbol (<equipos>)
			Element root = dom.getDocumentElement();
			//Localizamos todos los elementos <equipo>
			NodeList items = root.getElementsByTagName("equipo");
			//Recorremos la lista de Equipos
			for (int i=0; i<items.getLength(); i++){
				Equipo Equipo = new Equipo();
				//Obtenemos la Equipo actual
				Node item = items.item(i);
				//Obtenemos la lista de datos de la Equipo actual
				NodeList datosEquipo = item.getChildNodes();
				//Procesamos cada dato de la Equipo
				for (int j=0; j<datosEquipo.getLength(); j++){
					Node dato = datosEquipo.item(j);
					String etiqueta = dato.getNodeName();
					if (etiqueta.equals("nombre")){
						String texto = obtenerTexto(dato);
						Equipo.setNombre(texto);
					}
					else if (etiqueta.equals("presidente")) {
						Equipo.setPresidente(dato.getFirstChild().getNodeValue());
					}
					else if (etiqueta.equals("jugador1")){
						String texto = obtenerTexto(dato);
						Equipo.setJugador1(texto);
					}
					else if (etiqueta.equals("jugador2")){
						Equipo.setJugador2(dato.getFirstChild().getNodeValue());
					}
					else if (etiqueta.equals("jugador3")){
						Equipo.setJugador3(dato.getFirstChild().getNodeValue());
					}
				}
				Equipos.add(Equipo);
			}
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return Equipos;
	}
	
private String obtenerTexto(Node dato) {
	StringBuilder texto = new StringBuilder();
	NodeList fragmentos = dato.getChildNodes();
	for (int k=0;k<fragmentos.getLength();k++) {
		texto.append(fragmentos.item(k).getNodeValue());
	}
	return texto.toString();
}


}
