package org.android.cursoandroid.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class EquipoParserSAX {
	
	private InputStream fileInputStream;
	
	public EquipoParserSAX(InputStream fileInputStream) {
		try {
			this.fileInputStream = fileInputStream;
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Equipo> parse() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			EquipoHandler handler = new EquipoHandler();
			parser.parse(this.fileInputStream, handler);
			return handler.getEquipos();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
