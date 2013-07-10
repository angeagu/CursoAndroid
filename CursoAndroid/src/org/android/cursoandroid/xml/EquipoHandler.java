package org.android.cursoandroid.xml;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EquipoHandler extends DefaultHandler {
	private List<Equipo> equipos;
	private Equipo equipoActual;
	private StringBuilder sbTexto;
	
	public List<Equipo> getEquipos(){
		return equipos;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (this.equipoActual != null)
			sbTexto.append(ch, start, length);
		}

	@Override
	public void endElement(String uri, String localName, String name)
	throws SAXException {
		super.endElement(uri, localName, name);
		if (this.equipoActual != null) {
			if (localName.equals("nombre")) {
				equipoActual.setNombre(sbTexto.toString());
			} else if (localName.equals("presidente")) {
				equipoActual.setPresidente(sbTexto.toString());
			} else if (localName.equals("jugador1")) {
				equipoActual.setJugador1(sbTexto.toString());
			} else if (localName.equals("jugador2")) {
				equipoActual.setJugador2(sbTexto.toString());
			} else if (localName.equals("jugador3")) {
				equipoActual.setJugador3(sbTexto.toString());
			} else if (localName.equals("equipo")) {
				equipos.add(equipoActual);
			}
			sbTexto.setLength(0);
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		equipos = new ArrayList<Equipo>();
		sbTexto = new StringBuilder();
	}
	
	@Override
	public void startElement(String uri, String localName,
			String name, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		if (localName.equals("equipo")) {
			equipoActual = new Equipo();
		}
	}
}